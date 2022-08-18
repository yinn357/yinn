package top.yinn.logging.env;

import top.yinn.common.yml.CoreEnvironmentPostProcessor;

/**
 * 自定义加载yml类
 *
 * @Author Yinn
 */
public class LoggingEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	public LoggingEnvironmentPostProcessor() {
		super.setYmlName("logging.yml");
	}
}
