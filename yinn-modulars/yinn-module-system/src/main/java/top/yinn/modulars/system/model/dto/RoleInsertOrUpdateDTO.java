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


/**
 * 后台管理-新增/编辑角色 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑角色-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "角色名称", required = true)
	@NotBlank(message = "角色名称不能为空")
	private String name;

	@ApiModelProperty(value = "角色编码")
	private String code;

	@ApiModelProperty(value = "角色描述")
	private String describe;

	@ApiModelProperty(value = "是否内置角色 （1是 0否）")
	private Boolean readonly;

}
