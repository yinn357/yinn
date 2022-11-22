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
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_auth_menu")
public class MenuEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "`父菜单ID`")
    @TableField(value = "parent_id")
    private Long parentId;

    @ApiModelProperty(value = "`菜单标题`")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty(value = "`菜单名称`")
    @TableField(value = "menu_name")
    private String menuName;

    @ApiModelProperty(value = "`菜单图标`")
    @TableField(value = "icon")
    private String icon;

    @ApiModelProperty(value = "`路由地址`")
    @TableField(value = "path")
    private String path;

    @ApiModelProperty(value = "`重定向`")
    @TableField(value = "redirect")
    private String redirect;

    @ApiModelProperty(value = "`组件路径`")
    @TableField(value = "component")
    private String component;

    @ApiModelProperty(value = "`路由参数`")
    @TableField(value = "query_param")
    private String queryParam;

    @ApiModelProperty(value = "`权限标识`")
    @TableField(value = "permission")
    private String permission;

    @ApiModelProperty(value = "`菜单类型（D目录 M菜单 B按钮 E外链）`")
    @TableField(value = "menu_type")
    private String menuType;

    @ApiModelProperty(value = "`显示顺序`")
    @TableField(value = "order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "`是否缓存（0缓存 1不缓存）`")
    @TableField(value = "no_cache")
    private Boolean noCache;

    @ApiModelProperty(value = "`固定tag(1 固定 0 不固定) `")
    @TableField(value = "affix")
    private Boolean affix;

    @ApiModelProperty(value = "`菜单嵌套模式(1:只有一个子路由时当作跟路由)`")
    @TableField(value = "always_show")
    private Boolean alwaysShow;

    @ApiModelProperty(value = "`路由跳转（1即使hidden为true也可以跳转 0默认）`")
    @TableField(value = "can_to")
    private Boolean canTo;

    @ApiModelProperty(value = "`隐藏（1隐藏 0显示）`")
    @TableField(value = "hidden")
    private Boolean hidden;

    @ApiModelProperty(value = "`菜单状态（1正常 0停用）`")
    @TableField(value = "status")
    private Boolean status;

    @ApiModelProperty(value = "`备注`")
    @TableField(value = "remark")
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MenuEntity)) {
            return false;
        } else {
            MenuEntity other = (MenuEntity) o;
            if (this.getId() == null) {
                return false;
            }
            return this.getId().equals(other.getId());
        }
    }
}
