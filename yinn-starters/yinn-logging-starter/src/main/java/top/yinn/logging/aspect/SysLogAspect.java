package top.yinn.logging.aspect;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.yinn.core.context.UserContextHolder;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.ApiResult;
import top.yinn.logging.annotation.SysLog;
import top.yinn.logging.constant.OptLogConstant;
import top.yinn.logging.entity.OptLogDTO;
import top.yinn.logging.event.SysLogEvent;
import top.yinn.logging.util.LogUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 操作日志使用spring event异步入库
 *
 * @author Yinn
 */
@Slf4j
@Aspect
public class SysLogAspect {

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    private final ApplicationContext applicationContext = SpringUtil.getApplicationContext();

    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void sysLogAspect() {

    }

    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        tryCatch((val) -> {
            OptLogDTO sysLog = getOptLogDTO();
            Method controllerMethod = LogUtil.getControllerMethod(joinPoint);
            GetMapping getMapping = controllerMethod.getAnnotation(GetMapping.class);
            SysLog logAnnotation = controllerMethod.getAnnotation(SysLog.class);
            // GET请求接口 不发布操作日志保存事件
            if (getMapping != null) {
                sysLog.setIgnoreEvent(true);
            }
            // 存在@SysLog 发布操作日志保存事件
            if (logAnnotation != null) {
                sysLog.setIgnoreEvent(false);
            }

            // 用户信息
            sysLog.setCreateUser(UserContextHolder.getUserId());
            sysLog.setUserName(UserContextHolder.getUserName());

            // 接口信息
            String controllerDescription = "" ;
            Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
            if (api != null) {
                String[] tags = api.tags();
                if (tags != null && tags.length > 0) {
                    controllerDescription = tags[0];
                }
            }
            String controllerMethodDescription = controllerMethod.getAnnotation(ApiOperation.class).value();
            if (StrUtil.isEmpty(controllerDescription)) {
                sysLog.setDescription(controllerMethodDescription);
            } else {
                sysLog.setDescription(controllerDescription + "-" + controllerMethodDescription);
            }

            // 类名
            sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
            //获取执行的方法名
            sysLog.setActionMethod(joinPoint.getSignature().getName());


            // 参数
            Object[] args = joinPoint.getArgs();

            String strArgs = "";
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            try {
                if (!request.getContentType().contains("multipart/form-data")) {
                    strArgs = JSONObject.toJSONString(args);
                }
            } catch (Exception e) {
                try {
                    strArgs = Arrays.toString(args);
                } catch (Exception ex) {
                    log.warn("解析参数异常", ex);
                }
            }
            sysLog.setParams(getText(strArgs));

            // 请求信息
            if (request != null) {
                sysLog.setRequestIp(ServletUtil.getClientIP(request));
                sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
                sysLog.setHttpMethod(request.getMethod());
                sysLog.setUa(StrUtil.sub(request.getHeader("user-agent"), 0, 500));
            }
            // 开始时间
            sysLog.setStartTime(LocalDateTime.now());

            THREAD_LOCAL.set(sysLog);
        });
    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        tryCatch((aaa) -> {
            ApiResult result = ApiResult.success();
            if (ApiResult.class.isInstance(ret)) {
                result = Convert.convert(ApiResult.class, ret, ApiResult.success());
            }
            OptLogDTO sysLog = getOptLogDTO();
            if (result.getCode() == ExceptionCode.SUCCESS.getValue()) {
                sysLog.setType(OptLogConstant.Type.OPERATION);
            } else {
                sysLog.setType(OptLogConstant.Type.EXCEPTION);
                sysLog.setExDetail(result.getMsg());
            }
            sysLog.setResult(getText(JSONObject.toJSONString(result)));

            publishEvent(sysLog);
        });

    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        tryCatch((aaa) -> {
            OptLogDTO sysLog = getOptLogDTO();
            sysLog.setType(OptLogConstant.Type.EXCEPTION);

            // 异常对象
            sysLog.setExDetail(LogUtil.getStackTrace(e));
            // 异常信息
            sysLog.setExDesc(e.getMessage());

            publishEvent(sysLog);
        });
    }

    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }

    /**
     * 发布事件
     */
    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        // 是否需要发布事件操作日志的保存
        if (!BooleanUtil.isTrue(sysLog.getIgnoreEvent()) || OptLogConstant.Type.EXCEPTION.equals(sysLog.getType())) {
            applicationContext.publishEvent(new SysLogEvent(sysLog));
        }
        THREAD_LOCAL.remove();
    }

    /**
     * 获取ThreadLocal中的DTO
     */
    private OptLogDTO getOptLogDTO() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }

    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }


}
