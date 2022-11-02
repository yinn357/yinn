package top.yinn.redis.env;

import top.yinn.common.yml.CoreEnvironmentPostProcessor;

/**
 * @Author Yinn
 */
public class RedisEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	public RedisEnvironmentPostProcessor() {
		super.setYmlName("redis.yml");
	}
}
