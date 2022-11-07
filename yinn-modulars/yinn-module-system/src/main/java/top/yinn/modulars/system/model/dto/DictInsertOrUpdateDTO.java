package top.yinn.modulars.system.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 后台管理-新增/编辑字典表 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-新增/编辑字典表-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictInsertOrUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID", hidden = true, notes = "仅更新时使用")
	private Long id;

	@ApiModelProperty(value = "字典编号", required = true)
	@NotBlank(message = "字典编号不能为空")
	private String typeCode;

	@ApiModelProperty(value = "字典名称", required = true)
	@NotBlank(message = "字典名称不能为空")
	private String typeName;

	@ApiModelProperty(value = "是否内置 0否  1是", required = true)
	@NotBlank(message = "是否内置 0否  1是不能为空")
	private String izLock;

	@ApiModelProperty(value = "备注")
	private String remark;

}
