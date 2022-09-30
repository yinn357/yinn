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
 * 角色 VO
 *
 * @author Yinn
 */
@ApiModel(value = "角色")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "角色名称")
	private String name;

	@ApiModelProperty(value = "角色编码")
	private String code;

	@ApiModelProperty(value = "角色描述")
	private String describe;

	@ApiModelProperty(value = "是否内置角色 （1是 0否）")
	private Boolean readonly;

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime createTime;

	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime updateTime;

}
