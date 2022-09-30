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
 * 后台管理-分页列表角色分配 账号角色绑定 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表角色分配 账号角色绑定-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "角色ID #sys_auth_role")
    private Long roleId;

    @ApiModelProperty(value = "用户ID #sys_auth_user")
    private Long userId;

    @ApiModelProperty(value = "时间区间起")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "时间区间止")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime endTime;

}
