package top.yinn.logging.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * 只需在 @GetMapping 的接口上加
 * 描述会取 @ApiOperation.value
 *
 * @author Yinn
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 *
	 * @return {String}
	 */
	String value() default "" ;

	/**
	 * 记录执行参数
	 *
	 * @return
	 */
	boolean recordRequestParam() default true;

	/**
	 * 记录返回参数
	 *
	 * @return
	 */
	boolean recordResponseParam() default true;
}
