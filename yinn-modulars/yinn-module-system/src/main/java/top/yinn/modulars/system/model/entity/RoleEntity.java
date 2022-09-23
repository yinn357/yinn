package top.yinn.modulars.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.yinn.database.entity.BaseEntity;


/**
 * 角色
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_auth_role")
public class RoleEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "角色名称")
	@TableField(value = "name")
	private String name;

	@ApiModelProperty(value = "角色编码")
	@TableField(value = "code")
	private String code;

	@ApiModelProperty(value = "角色描述")
	@TableField(value = "describe")
	private String describe;

	@ApiModelProperty(value = "是否内置角色 （1是 0否）")
	@TableField(value = "readonly")
	private Boolean readonly;

}
