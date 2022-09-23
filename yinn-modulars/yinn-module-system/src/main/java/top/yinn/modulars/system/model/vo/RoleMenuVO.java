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
 * 角色的菜单/权限 BO
 *
 * @author YinnInsertOrUpdate
 */
@ApiModel(value = "角色的菜单/权限")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "菜单/权限id #sys_auth_menu")
	private Long menuId;

	@ApiModelProperty(value = "角色id#sys_auth_role")
	private Long roleId;

	@ApiModelProperty(value = "创建人")
	private Long createUser;

}
