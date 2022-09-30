package top.yinn.modulars.system.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import top.yinn.core.context.UserContext;
import top.yinn.core.context.UserContextHolder;
import top.yinn.core.utils.IPUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从请求头解析并赋值到用户上下文
 *
 * @Author Yinn
 */
@Slf4j
public class SaTokenParseInterceptor implements AsyncHandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof ResourceHttpRequestHandler) {
			// 直接放行静态资源
			return true;
		}
		// SA-Token 会自动从请求头中解析 token，所以这里可以直接拿到对应 session，从而取出业务字段
		if (StpUtil.isLogin()) {
			UserContext userContext = (UserContext) StpUtil.getSession().get(UserContext.CAMEL_NAME);

			// 获取用户公网IP
			userContext.setClientIP(IPUtil.getClientIPAddress(request));
			UserContextHolder.setUserContext(userContext);

			log.debug("[SA-Token] 从请求头解析出用户上下文 >> {}", userContext);

		} else {
			UserContextHolder.setUserContext(null);
		}
		return true;
	}
}
