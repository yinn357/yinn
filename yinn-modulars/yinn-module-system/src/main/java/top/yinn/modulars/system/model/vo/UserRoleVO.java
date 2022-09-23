package top.yinn.modulars.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


/**
 * 角色分配
 * 账号角色绑定 BO
 *
 * @author YinnInsertOrUpdate
 */
@ApiModel(value = "角色分配 账号角色绑定")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "角色ID #sys_auth_role")
	private Long roleId;

	@ApiModelProperty(value = "用户ID#sys_auth_user")
	private Long userId;

	@ApiModelProperty(value = "创建人ID")
	private Long createUser;

}
