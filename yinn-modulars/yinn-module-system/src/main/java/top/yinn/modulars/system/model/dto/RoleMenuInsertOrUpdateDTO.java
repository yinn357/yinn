package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 后台管理-新增/编辑角色的菜单/权限 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑角色的菜单/权限-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMenuInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "菜单/权限id #sys_auth_menu", required = true)
	@NotNull(message = "菜单/权限id #sys_auth_menu不能为空")
	private Long menuId;

	@ApiModelProperty(value = "角色id #sys_auth_role", required = true)
	@NotNull(message = "角色id #sys_auth_role不能为空")
	private Long roleId;

}
