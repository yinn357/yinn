package top.yinn.modulars.system.model.vo;

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
 * 登录日志 VO
 *
 * @author Yinn
 */
@ApiModel(value = "登录日志")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginLogVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
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

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime createTime;

}
