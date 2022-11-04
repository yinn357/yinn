package top.yinn.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.yinn.core.common.config.BaseConfig;
import top.yinn.modulars.system.interceptor.SaTokenParseInterceptor;

/**
 * Yinn公共基础配置
 *
 * @Author
 */
@Configuration
public class WebConfiguration extends BaseConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 请求头解析, 设定用户上下文
		registry.addInterceptor(new SaTokenParseInterceptor()).addPathPatterns("/**");

		// 注册Sa-Token的注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
		registry.addInterceptor(new SaInterceptor(handler -> {
			// 根据路由划分模块，不同模块不同鉴权
			// 1.后台-路由拦截器, 使几乎所有接口都需要登录
			// SaRouter.match(SysConstant.SYS_MODULE_CONTEXT_PATH + "/**", r -> StpUtil.checkLogin());
		})).addPathPatterns("/**");
	}
}
