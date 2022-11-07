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
 * 系统日志
 *
 * @author Yinn
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "sys_common_operate_log")
public class OperateLogEntity extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "`操作IP`")
    @TableField(value = "request_ip")
    private String requestIp;

    @ApiModelProperty(value = "`日志类型 #LogType{OPT:操作类型;EX:异常类型}`")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "`操作人`")
    @TableField(value = "user_name")
    private String userName;

    @ApiModelProperty(value = "`操作描述`")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty(value = "`类路径`")
    @TableField(value = "class_path")
    private String classPath;

    @ApiModelProperty(value = "`请求方法`")
    @TableField(value = "action_method")
    private String actionMethod;

    @ApiModelProperty(value = "`请求地址`")
    @TableField(value = "request_uri")
    private String requestUri;

    @ApiModelProperty(value = "`请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}`")
    @TableField(value = "http_method")
    private String httpMethod;

    @ApiModelProperty(value = "`请求参数`")
    @TableField(value = "params")
    private String params;

    @ApiModelProperty(value = "`返回值`")
    @TableField(value = "result")
    private String result;

    @ApiModelProperty(value = "`异常详情信息`")
    @TableField(value = "ex_desc")
    private String exDesc;

    @ApiModelProperty(value = "`异常描述`")
    @TableField(value = "ex_detail")
    private String exDetail;

    @ApiModelProperty(value = "`开始时间`")
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "`完成时间`")
    @TableField(value = "finish_time")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "`消耗时间`")
    @TableField(value = "consuming_time")
    private Long consumingTime;

    @ApiModelProperty(value = "`浏览器`")
    @TableField(value = "ua")
    private String ua;

}
