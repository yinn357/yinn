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
 * 后台管理-分页列表字典表-明细 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表字典表-明细-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "类型ID(关键词)")
	private String typeId;

	@ApiModelProperty(value = "类型code 冗余字段(关键词)")
	private String typeCode;

	@ApiModelProperty(value = "字典名称(关键词)")
	private String dictName;

	@ApiModelProperty(value = "字典值code #非必填,用于部分特殊配置(关键词)")
	private String dictCode;

	@ApiModelProperty(value = "字典值(关键词)")
	private String dictValue;

	@ApiModelProperty(value = "是否内置 0否  1是(关键词)")
	private Boolean izLock;

	@ApiModelProperty(value = "排序")
	private Integer sortNo;

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
