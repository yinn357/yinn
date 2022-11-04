package top.yinn.satoken.env;

import top.yinn.core.common.yml.CoreEnvironmentPostProcessor;

/**
 * @Author Yinn
 */
public class SaTokenEnvironmentPostProcessor extends CoreEnvironmentPostProcessor {
	public SaTokenEnvironmentPostProcessor() {
		super.setYmlName("sa-token.yml");
	}
}
