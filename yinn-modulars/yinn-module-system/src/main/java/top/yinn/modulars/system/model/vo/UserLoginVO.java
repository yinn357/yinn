package top.yinn.modulars.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Yinn
 */
@ApiModel(value = "用户登录-返回")
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginVO {

	@ApiModelProperty(value = "用户ID")
	private Long id;

	@ApiModelProperty(value = "账号")
	private String account;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "对应角色ID")
	private Set<Long> roleIds;

	@ApiModelProperty(value = "对应角色名")
	private List<String> roles;

	@ApiModelProperty(value = "所有拥有权限名")
	private Set<String> permissions;

	@ApiModelProperty(value = "角色ID-对应权限名 Map")
	private Map<Long, Set<String>> roleIdPermissionMap;

}
