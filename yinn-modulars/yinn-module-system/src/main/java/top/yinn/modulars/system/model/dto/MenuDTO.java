package top.yinn.modulars.system.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import top.yinn.core.constant.YinnConstant;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 后台管理-分页列表菜单权限表 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表菜单权限表-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单标题(关键词)")
    private String title;

    @ApiModelProperty(value = "菜单名称(关键词)")
    private String menuName;

    @ApiModelProperty(value = "菜单图标(关键词)")
    private String icon;

    @ApiModelProperty(value = "路由地址(关键词)")
    private String path;

    @ApiModelProperty(value = "重定向(关键词)")
    private String redirect;

    @ApiModelProperty(value = "组件路径(关键词)")
    private String component;

    @ApiModelProperty(value = "路由参数(关键词)")
    private String queryParam;

    @ApiModelProperty(value = "权限标识(关键词)")
    private String permission;

    @ApiModelProperty(value = "菜单类型（D目录 M菜单 B按钮 E外链）(关键词)")
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

    @ApiModelProperty(value = "备注(关键词)")
    private String remark;

    @ApiModelProperty(value = "时间区间起")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "时间区间止")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime endTime;

}
