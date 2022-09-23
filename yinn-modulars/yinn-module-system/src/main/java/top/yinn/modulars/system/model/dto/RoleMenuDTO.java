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
 * 后台管理-分页列表角色的菜单/权限 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表角色的菜单/权限-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleMenuDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "菜单/权限id #sys_auth_menu")
	private Long menuId;

	@ApiModelProperty(value = "角色id #sys_auth_role")
	private Long roleId;

	@ApiModelProperty(value = "时间区间起")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime beginAt;

	@ApiModelProperty(value = "时间区间止")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime endAt;

}
