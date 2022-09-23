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
 * 后台管理-新增/编辑角色分配
 * 账号角色绑定 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑角色分配 账号角色绑定-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "角色ID #sys_auth_role", required = true)
	@NotNull(message = "角色ID能为空")
	private Long roleId;

	@ApiModelProperty(value = "用户ID#sys_auth_user", required = true)
	@NotNull(message = "用户ID不能为空")
	private Long userId;

}
