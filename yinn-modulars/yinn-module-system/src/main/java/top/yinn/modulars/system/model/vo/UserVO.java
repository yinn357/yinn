package top.yinn.modulars.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户 BO
 *
 * @author YinnInsertOrUpdate
 */
@ApiModel(value = "用户")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "账号")
	private String account;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "昵称")
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

	@ApiModelProperty(value = "创建人id")
	private Long createUser;

	@ApiModelProperty(value = "更新人id")
	private Long updateUser;

}
