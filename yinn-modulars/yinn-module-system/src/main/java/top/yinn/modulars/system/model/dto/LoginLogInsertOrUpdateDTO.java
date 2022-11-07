package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 后台管理-新增/编辑登录日志 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑登录日志-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginLogInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "操作IP")
	private String requestIp;

	@ApiModelProperty(value = "登录人ID")
	private Long userId;

	@ApiModelProperty(value = "登录人姓名")
	private String userName;

	@ApiModelProperty(value = "登录人账号")
	private String account;

	@ApiModelProperty(value = "登录描述")
	private String description;

	@ApiModelProperty(value = "登录时间")
	private LocalDateTime loginDate;

	@ApiModelProperty(value = "浏览器请求头")
	private String ua;

	@ApiModelProperty(value = "浏览器名称")
	private String browser;

	@ApiModelProperty(value = "浏览器版本")
	private String browserVersion;

	@ApiModelProperty(value = "操作系统")
	private String operatingSystem;

	@ApiModelProperty(value = "登录地点")
	private String location;

}
