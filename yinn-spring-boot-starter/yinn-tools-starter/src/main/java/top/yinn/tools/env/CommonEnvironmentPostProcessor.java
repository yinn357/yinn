package top.yinn.tools.env;

import top.yinn.common.yml.CoreEnvironmentPostProcessor;

/**
 * @Author Yinn
 */
public class CommonEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	public CommonEnvironmentPostProcessor() {
		super.setYmlName("common.yml");
	}
}
