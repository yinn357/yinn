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
 * 系统日志 VO
 *
 * @author Yinn
 */
@ApiModel(value = "系统日志")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperateLogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "操作IP")
    private String requestIp;

    @ApiModelProperty(value = "日志类型 #LogType{OPT:操作类型;EX:异常类型}")
    private String type;

    @ApiModelProperty(value = "操作人")
    private String userName;

    @ApiModelProperty(value = "操作描述")
    private String description;

    @ApiModelProperty(value = "类路径")
    private String classPath;

    @ApiModelProperty(value = "请求方法")
    private String actionMethod;

    @ApiModelProperty(value = "请求地址")
    private String requestUri;

    @ApiModelProperty(value = "请求类型 #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}")
    private String httpMethod;

    @ApiModelProperty(value = "请求参数")
    private String params;

    @ApiModelProperty(value = "返回值")
    private String result;

    @ApiModelProperty(value = "异常详情信息")
    private String exDesc;

    @ApiModelProperty(value = "异常描述")
    private String exDetail;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "消耗时间")
    private Long consumingTime;

    @ApiModelProperty(value = "浏览器")
    private String ua;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

}
