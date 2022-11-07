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
 * 后台管理-分页列表登录日志 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表登录日志-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginLogDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "操作IP(关键词)")
	private String requestIp;

	@ApiModelProperty(value = "登录人ID")
	private Long userId;

	@ApiModelProperty(value = "登录人姓名(关键词)")
	private String userName;

	@ApiModelProperty(value = "登录人账号(关键词)")
	private String account;

	@ApiModelProperty(value = "登录描述(关键词)")
	private String description;

	@ApiModelProperty(value = "登录时间")
	private LocalDateTime loginDate;

	@ApiModelProperty(value = "浏览器请求头(关键词)")
	private String ua;

	@ApiModelProperty(value = "浏览器名称(关键词)")
	private String browser;

	@ApiModelProperty(value = "浏览器版本(关键词)")
	private String browserVersion;

	@ApiModelProperty(value = "操作系统(关键词)")
	private String operatingSystem;

	@ApiModelProperty(value = "登录地点(关键词)")
	private String location;

	@ApiModelProperty(value = "时间区间起")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime beginTime;

	@ApiModelProperty(value = "时间区间止")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime endTime;

}
