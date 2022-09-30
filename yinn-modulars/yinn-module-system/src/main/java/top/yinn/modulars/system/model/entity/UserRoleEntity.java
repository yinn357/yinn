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
 * 角色分配 账号角色绑定
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_auth_user_role")
public class UserRoleEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "角色ID #sys_auth_role")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户ID#sys_auth_user")
    @TableField(value = "user_id")
    private Long userId;

}
