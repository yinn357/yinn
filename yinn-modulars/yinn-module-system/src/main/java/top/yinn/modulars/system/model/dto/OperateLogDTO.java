package top.yinn.modulars.system.model.dto;

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
 * 后台管理-分页列表系统日志 DTO
 *
 * @author Yinn
 */
@ApiModel(value = "后台管理-分页列表系统日志-入参")
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperateLogDTO implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "操作IP(关键词)")
	private String requestIp;

	@ApiModelProperty(value = "日志类型#LogType{OPT:操作类型;EX:异常类型}(关键词)")
	private String type;

	@ApiModelProperty(value = "操作人(关键词)")
	private String userName;

	@ApiModelProperty(value = "操作描述(关键词)")
	private String description;

	@ApiModelProperty(value = "类路径(关键词)")
	private String classPath;

	@ApiModelProperty(value = "请求方法(关键词)")
	private String actionMethod;

	@ApiModelProperty(value = "请求地址(关键词)")
	private String requestUri;

	@ApiModelProperty(value = "请求类型#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}(关键词)")
	private String httpMethod;

	@ApiModelProperty(value = "请求参数(关键词)")
	private String params;

	@ApiModelProperty(value = "返回值(关键词)")
	private String result;

	@ApiModelProperty(value = "异常详情信息(关键词)")
	private String exDesc;

	@ApiModelProperty(value = "异常描述(关键词)")
	private String exDetail;

	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;

	@ApiModelProperty(value = "完成时间")
	private LocalDateTime finishTime;

	@ApiModelProperty(value = "消耗时间")
	private Long consumingTime;

	@ApiModelProperty(value = "浏览器(关键词)")
	private String ua;

	@ApiModelProperty(value = "时间区间起")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime beginTime;

	@ApiModelProperty(value = "时间区间止")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	@DateTimeFormat(pattern = YinnConstant.Jackson.DATE_TIME_FORMAT)
	private LocalDateTime endTime;

}
