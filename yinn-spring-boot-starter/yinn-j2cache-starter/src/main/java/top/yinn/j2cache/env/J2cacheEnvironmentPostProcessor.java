package top.yinn.j2cache.env;

import top.yinn.common.yml.CoreEnvironmentPostProcessor;

/**
 * @Author Yinn
 */
public class J2cacheEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	/**
	 * <p>Constructor for J2cacheEnvironmentPostProcessor</p>
	 */
	public J2cacheEnvironmentPostProcessor() {
		super.setYmlName("j2cache.yml");
	}
}
