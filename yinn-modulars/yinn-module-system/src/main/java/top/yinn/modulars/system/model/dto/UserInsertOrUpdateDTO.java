package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 后台管理-新增/编辑用户 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑用户-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "账号", required = true)
	@NotBlank(message = "账号不能为空")
	private String account;

	@ApiModelProperty(value = "密码", required = true)
	@NotBlank(message = "密码不能为空")
	private String password;

	@ApiModelProperty(value = "昵称", required = true)
	@NotBlank(message = "昵称不能为空")
	private String nickName;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "性别 #Sex{W:女;M:男;N:未知}")
	private String sex;

	@ApiModelProperty(value = "头像")
	private String avatar;

	@ApiModelProperty(value = "最后登录时间")
	private LocalDateTime lastLoginTime;

}
