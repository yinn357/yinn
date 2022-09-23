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
 * 后台管理-分页列表用户 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表用户-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "账号(关键词)")
	private String account;

	@ApiModelProperty(value = "密码(关键词)")
	private String password;

	@ApiModelProperty(value = "昵称(关键词)")
	private String nickName;

	@ApiModelProperty(value = "邮箱(关键词)")
	private String email;

	@ApiModelProperty(value = "手机(关键词)")
	private String mobile;

	@ApiModelProperty(value = "性别 #Sex{W:女;M:男;N:未知}(关键词)")
	private String sex;

	@ApiModelProperty(value = "头像(关键词)")
	private String avatar;

	@ApiModelProperty(value = "最后登录时间")
	private LocalDateTime lastLoginTime;

	@ApiModelProperty(value = "时间区间起")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime beginAt;

	@ApiModelProperty(value = "时间区间止")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime endAt;

}
