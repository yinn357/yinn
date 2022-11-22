package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author Yinn
 */
@ApiModel(value = "用户登录-入参")
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDTO {

	@ApiModelProperty(value = "账号", required = true)
	@Size(min = 4, max = 16, message = "【账号】长度须在 4 至 16 位之间")
	@NotBlank(message = "账号不能为空")
	private String account;

	@ApiModelProperty(value = "密码", required = true)
	@Size(min = 6, max = 64, message = "【密码】长度须在 6 至 16 位之间")
	@NotBlank(message = "密码不能为空")
	private String password;

	@ApiModelProperty(value = "记住我")
	private Boolean rememberMe;

	@ApiModelProperty(value = "验证码图片UUID(可选，需自行对接业务逻辑)")
	private String captchaUUID;

	@ApiModelProperty(value = "验证码答案(可选，需自行对接业务逻辑)")
	private String captchaAnswer;

	@ApiModelProperty(value = "客户端IP地址", hidden = true)
	private String clientIP;
}
