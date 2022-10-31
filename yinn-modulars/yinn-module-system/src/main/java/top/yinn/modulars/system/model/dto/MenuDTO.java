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


    @ApiModelProperty(value = "菜单名称(关键词)")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "路由地址(关键词)")
    private String path;

    @ApiModelProperty(value = "组件路径(关键词)")
    private String component;

    @ApiModelProperty(value = "路由参数(关键词)")
    private String queryParam;

    @ApiModelProperty(value = "是否为外链（1是 0否）")
    private Boolean isFrame;

    @ApiModelProperty(value = "是否缓存（1缓存 0不缓存）")
    private Boolean isCache;

    @ApiModelProperty(value = "菜单类型（D目录 M菜单 B按钮 E外链）(关键词)")
    private String menuType;

    @ApiModelProperty(value = "菜单状态（1显示 0隐藏）")
    private Boolean visible;

    @ApiModelProperty(value = "菜单状态（1正常 0停用）")
    private Boolean status;

    @ApiModelProperty(value = "权限标识(关键词)")
    private String perms;

    @ApiModelProperty(value = "菜单图标(关键词)")
    private String icon;

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
