package top.yinn.redis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 保证redis在异常的时候，系统依然可以工作
 *
 * @Author Yinn
 */
@Aspect
@Slf4j
public class RedisAspect {
	@Pointcut("execution(* top.yinn.redis.util.*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object handleException(ProceedingJoinPoint joinPoint) {
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			log.error("redis好像出错了");
		}
		return result;
	}
}
