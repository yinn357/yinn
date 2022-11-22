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
 * 菜单路由配置
 *
 * @Author Yinn
 */
@ApiModel(value = "菜单路由配置")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuMetaVo implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "侧边栏隐藏")
	private Boolean hidden;

	@ApiModelProperty(value = "菜单嵌套模式(1:只有一个子路由时当作跟路由)")
	private Boolean alwaysShow;

	@ApiModelProperty(value = "菜单标题")
	private String title;

	@ApiModelProperty(value = "菜单图标")
	private String icon;

	@ApiModelProperty(value = "不缓存")
	private Boolean noCache;

	@ApiModelProperty(value = "面包屑中显示")
	private Boolean breadcrumb;

	@ApiModelProperty(value = "固定在Tag")
	private Boolean affix;

	@ApiModelProperty(value = "不会出现在tag")
	private Boolean noTagsView;

	@ApiModelProperty(value = "显示高亮的路由路径")
	private String activeMenu;

	@ApiModelProperty(value = "跟随哪个路由进行权限过滤")
	private String followAuth;

	@ApiModelProperty(value = "路由跳转（1即使hidden为true也可以跳转 0默认）")
	private Boolean canTo;
}
