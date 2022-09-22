package top.yinn.modulars.test.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.yinn.database.entity.BaseEntity;

import java.time.LocalDateTime;


/**
 * 用户
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_auth_user")
public class AuthUserEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "账号")
	@TableField(value = "account")
	private String account;

	@ApiModelProperty(value = "密码")
	@TableField(value = "password")
	private String password;

	@ApiModelProperty(value = "姓名")
	@TableField(value = "name")
	private String name;

	@ApiModelProperty(value = "组织ID#c_core_org")
	@TableField(value = "org_id")
	private Long orgId;

	@ApiModelProperty(value = "岗位ID#c_core_station")
	@TableField(value = "station_id")
	private Long stationId;

	@ApiModelProperty(value = "邮箱")
	@TableField(value = "email")
	private String email;

	@ApiModelProperty(value = "手机")
	@TableField(value = "mobile")
	private String mobile;

	@ApiModelProperty(value = "性别#Sex{W:女;M:男;N:未知}")
	@TableField(value = "sex")
	private String sex;

	@ApiModelProperty(value = "启用状态 1启用 0禁用")
	@TableField(value = "status")
	private Boolean status;

	@ApiModelProperty(value = "头像")
	@TableField(value = "avatar")
	private String avatar;

	@ApiModelProperty(value = "最后登录时间")
	@TableField(value = "last_login_time")
	private LocalDateTime lastLoginTime;


}
