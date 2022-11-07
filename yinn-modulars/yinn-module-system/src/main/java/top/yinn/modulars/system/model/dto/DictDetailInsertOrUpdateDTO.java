package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 后台管理-新增/编辑字典表-明细 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑字典表-明细-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictDetailInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "类型ID", required = true)
	@NotBlank(message = "类型ID不能为空")
	private String typeId;

	@ApiModelProperty(value = "类型code 冗余字段", required = true)
	@NotBlank(message = "类型code 冗余字段不能为空")
	private String typeCode;

	@ApiModelProperty(value = "字典名称", required = true)
	@NotBlank(message = "字典名称不能为空")
	private String dictName;

	@ApiModelProperty(value = "字典值code #非必填,用于部分特殊配置")
	private String dictCode;

	@ApiModelProperty(value = "字典值", required = true)
	@NotBlank(message = "字典值不能为空")
	private String dictValue;

	@ApiModelProperty(value = "是否内置 0否  1是", required = true)
	@NotBlank(message = "是否内置 0否  1是不能为空")
	private String izLock;

	@ApiModelProperty(value = "排序", required = true)
	@NotNull(message = "排序不能为空")
	private Integer sortNo;

	@ApiModelProperty(value = "备注")
	private String remark;

}
