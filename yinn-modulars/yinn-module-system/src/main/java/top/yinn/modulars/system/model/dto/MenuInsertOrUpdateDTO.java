package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(value = "父菜单ID", required = true)
    @NotNull(message = "父菜单ID不能为空")
    private Long parentId;

    @ApiModelProperty(value = "菜单标题", required = true)
    @NotBlank(message = "菜单标题不能为空")
    private String title;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "路由参数")
    private String queryParam;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "菜单类型（D目录 M菜单 B按钮 E外链）")
    private String menuType;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "是否缓存（0缓存 1不缓存）")
    private Boolean noCache;

    @ApiModelProperty(value = "固定tag(1 固定 0 不固定) ")
    private Boolean affix;

    @ApiModelProperty(value = "菜单嵌套模式(1:只有一个子路由时当作跟路由)")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "路由跳转（1即使hidden为true也可以跳转 0默认）")
    private Boolean canTo;

    @ApiModelProperty(value = "隐藏（1隐藏 0显示）")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单状态（1正常 0停用）")
    private Boolean status;

    @ApiModelProperty(value = "备注")
    private String remark;

}
