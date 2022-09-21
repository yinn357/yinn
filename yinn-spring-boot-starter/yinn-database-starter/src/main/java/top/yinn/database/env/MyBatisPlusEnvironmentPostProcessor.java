package top.yinn.database.env;

import top.yinn.common.yml.CoreEnvironmentPostProcessor;

/**
 * @Author Yinn
 */
public class MyBatisPlusEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	/**
	 * <p>Constructor for J2cacheEnvironmentPostProcessor</p>
	 */
	public MyBatisPlusEnvironmentPostProcessor() {
		super.setYmlName("mybatis-plus.yml");
	}
}
