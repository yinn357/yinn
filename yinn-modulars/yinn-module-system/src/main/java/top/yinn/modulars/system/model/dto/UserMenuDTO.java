package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 权限管理-用户菜单权限-入参
 *
 * @Author Yinn
 */
@ApiModel(value = "权限管理-用户菜单权限-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserMenuDTO implements Serializable {


	@ApiModelProperty(value = "角色Id")
	private Set<Long> roleIds;

	@ApiModelProperty(value = "菜单Id")
	private Set<Long> menuIds;

	@ApiModelProperty(value = "排除菜单类型（D目录 M菜单 B按钮 E外链）")
	private List<String> notMenuTypeList;

	@ApiModelProperty(value = "包含菜单类型（D目录 M菜单 B按钮 E外链）")
	private List<String> menuTypeList;
}
