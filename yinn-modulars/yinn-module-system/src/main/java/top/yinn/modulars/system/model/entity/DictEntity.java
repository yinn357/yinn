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
 * 字典表
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_common_dict")
public class DictEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "`字典编号`")
	@TableField(value = "type_code")
	private String typeCode;

	@ApiModelProperty(value = "`字典名称`")
	@TableField(value = "type_name")
	private String typeName;

	@ApiModelProperty(value = "`是否内置 0否  1是`")
	@TableField(value = "iz_lock")
	private String izLock;

	@ApiModelProperty(value = "`备注`")
	@TableField(value = "remark")
	private String remark;

}
