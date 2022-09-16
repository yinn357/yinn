package top.yinn.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.yinn.common.handler.DefaultGlobalExceptionHandler;

/**
 * 权限服务中使用的全局异常处理配置类
 *
 * @Author Yinn
 */
@Configuration
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class ExceptionConfiguration extends DefaultGlobalExceptionHandler {
}
