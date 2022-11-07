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
import top.yinn.database.entity.SuperEntity;

import java.time.LocalDateTime;


/**
 * 登录日志
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_common_login_log")
public class LoginLogEntity extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "`操作IP`")
    @TableField(value = "request_ip")
    private String requestIp;

    @ApiModelProperty(value = "`登录人ID`")
    @TableField(value = "user_id")
    private Long userId;

    @ApiModelProperty(value = "`登录人姓名`")
    @TableField(value = "user_name")
    private String userName;

    @ApiModelProperty(value = "`登录人账号`")
    @TableField(value = "account")
    private String account;

    @ApiModelProperty(value = "`登录描述`")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "`登录时间`")
    @TableField(value = "login_date")
    private LocalDateTime loginDate;

    @ApiModelProperty(value = "`浏览器请求头`")
    @TableField(value = "ua")
    private String ua;

    @ApiModelProperty(value = "`浏览器名称`")
    @TableField(value = "browser")
    private String browser;

    @ApiModelProperty(value = "`浏览器版本`")
    @TableField(value = "browser_version")
    private String browserVersion;

    @ApiModelProperty(value = "`操作系统`")
    @TableField(value = "operating_system")
    private String operatingSystem;

    @ApiModelProperty(value = "`登录地点`")
    @TableField(value = "location")
    private String location;

}
