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
 * 字典表-明细 VO
 *
 * @author Yinn
 */
@ApiModel(value = "字典表-明细")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "类型ID")
	private String typeId;

	@ApiModelProperty(value = "类型code 冗余字段")
	private String typeCode;

	@ApiModelProperty(value = "字典名称")
	private String dictName;

	@ApiModelProperty(value = "字典值code #非必填,用于部分特殊配置")
	private String dictCode;

	@ApiModelProperty(value = "字典值")
	private String dictValue;

	@ApiModelProperty(value = "是否内置 0否  1是")
	private String izLock;

	@ApiModelProperty(value = "排序")
	private Integer sortNo;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime createTime;

	@ApiModelProperty(value = "更新时间")
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime updateTime;

}
