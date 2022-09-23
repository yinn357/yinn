package top.yinn.modulars.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.yinn.database.entity.SuperEntity;


/**
 * 角色的菜单/权限
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_auth_role_menu")
public class RoleMenuEntity extends SuperEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "菜单/权限id #sys_auth_menu")
	@TableField(value = "menu_id")
	private Long menuId;

	@ApiModelProperty(value = "角色id #sys_auth_role")
	@TableField(value = "role_id")
	private Long roleId;

}
