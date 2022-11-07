package top.yinn.modulars.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.yinn.database.entity.BaseEntity;


/**
 * 字典表-明细
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_common_dict_detail")
public class DictDetailEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "`类型ID`")
	@TableField(value = "type_id")
	private String typeId;

	@ApiModelProperty(value = "`类型code 冗余字段`")
	@TableField(value = "type_code")
	private String typeCode;

	@ApiModelProperty(value = "`字典名称`")
	@TableField(value = "dict_name")
	private String dictName;

	@ApiModelProperty(value = "`字典值code #非必填,用于部分特殊配置`")
	@TableField(value = "dict_code")
	private String dictCode;

	@ApiModelProperty(value = "`字典值`")
	@TableField(value = "dict_value")
	private String dictValue;

	@ApiModelProperty(value = "`是否内置 0否  1是`")
	@TableField(value = "iz_lock")
	private Boolean izLock;

	@ApiModelProperty(value = "`排序`")
	@TableField(value = "sort_no")
	private Integer sortNo;

	@ApiModelProperty(value = "`备注`")
	@TableField(value = "remark")
	private String remark;

}
