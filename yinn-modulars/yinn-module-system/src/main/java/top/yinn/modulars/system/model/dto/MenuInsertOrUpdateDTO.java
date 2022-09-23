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
 * 后台管理-新增/编辑菜单权限表 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑菜单权限表-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "菜单名称", required = true)
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;

	@ApiModelProperty(value = "父菜单ID")
	private Long parentId;

	@ApiModelProperty(value = "显示顺序")
	private Integer orderNum;

	@ApiModelProperty(value = "路由地址")
	private String path;

	@ApiModelProperty(value = "组件路径")
	private String component;

	@ApiModelProperty(value = "路由参数")
	private String queryParam;

	@ApiModelProperty(value = "是否为外链（1是 0否）")
	private Boolean isFrame;

	@ApiModelProperty(value = "是否缓存（1缓存 0不缓存）")
	private Boolean isCache;

	@ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
	private String menuType;

	@ApiModelProperty(value = "菜单状态（1显示 0隐藏）")
	private Boolean visible;

	@ApiModelProperty(value = "菜单状态（1正常 0停用）")
	private Boolean status;

	@ApiModelProperty(value = "权限标识")
	private String perms;

	@ApiModelProperty(value = "菜单图标")
	private String icon;

	@ApiModelProperty(value = "备注")
	private String remark;

}
