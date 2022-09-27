package top.yinn.modulars.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.yinn.database.entity.BaseEntity;


/**
 * 菜单权限表
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@TableName(value = "sys_auth_menu")
public class MenuEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "菜单名称")
	@TableField(value = "menu_name")
	private String menuName;

	@ApiModelProperty(value = "父菜单ID")
	@TableField(value = "parent_id")
	private Long parentId;

	@ApiModelProperty(value = "显示顺序")
	@TableField(value = "order_num")
	private Integer orderNum;

	@ApiModelProperty(value = "路由地址")
	@TableField(value = "path")
	private String path;

	@ApiModelProperty(value = "组件路径")
	@TableField(value = "component")
	private String component;

	@ApiModelProperty(value = "路由参数")
	@TableField(value = "query_param")
	private String queryParam;

	@ApiModelProperty(value = "是否为外链（1是 0否）")
	@TableField(value = "is_frame")
	private Boolean isFrame;

	@ApiModelProperty(value = "是否缓存（1缓存 0不缓存）")
	@TableField(value = "is_cache")
	private Boolean isCache;

	@ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
	@TableField(value = "menu_type")
	private String menuType;

	@ApiModelProperty(value = "菜单状态（1显示 0隐藏）")
	@TableField(value = "visible")
	private Boolean visible;

	@ApiModelProperty(value = "菜单状态（1正常 0停用）")
	@TableField(value = "status")
	private Boolean status;

	@ApiModelProperty(value = "权限标识")
	@TableField(value = "perms")
	private String perms;

	@ApiModelProperty(value = "菜单图标")
	@TableField(value = "icon")
	private String icon;

	@ApiModelProperty(value = "备注")
	@TableField(value = "remark")
	private String remark;

}
