package top.yinn.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Yinn
 * Tools starter 配置
 * 必须配置 prefix ，才能有提示
 */
@Data
@ConfigurationProperties(prefix = "yinn")
public class ToolsProperties {


	/**
	 * Dozer Bean转换启动配置
	 */
	private Dozer dozer = new Dozer();
	/**
	 * xss攻击启动配置
	 */
	private Xss xss = new Xss();
	/**
	 * validator 表单校验快速启动配置
	 */
	private Validator validator = new Validator();

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Dozer {
		private Boolean enable = true;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Xss {
		private Boolean enable = true;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Validator {
		private Boolean enable = true;
	}
}
