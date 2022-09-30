package top.yinn.core.exception.code;


import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.enums.BaseEnum;

/**
 * 系统异常枚举类
 *
 * @author Yinn
 */
@Getter
@AllArgsConstructor
public enum ExceptionCode implements BaseEnum<Integer> {

	//系统相关 start
	SUCCESS(HttpStatus.HTTP_OK, YinnConstant.Message.SUCCESS),
	LOGIN_SUCCESS(HttpStatus.HTTP_OK, "登录成功"),

	SYSTEM_BUSY(500, "系统繁忙~请稍后再试~"),
	SYSTEM_TIMEOUT(503, "系统维护中~请稍后再试~"),
	PARAM_EX(500, "参数类型解析异常"),
	SQL_EX(500, "运行SQL出现异常"),
	NULL_POINT_EX(500, "空指针异常"),
	ILLEGALA_ARGUMENT_EX(500, "无效参数异常"),
	MEDIA_TYPE_EX(500, "请求类型异常"),
	LOAD_RESOURCES_ERROR(500, "加载资源出错"),
	BASE_VALID_PARAM(500, "统一验证参数异常"),
	OPERATION_EX(500, "操作异常"),
	REQUIRED_FILE_PARAM_EX(500, "请求中必须至少包含一个有效文件"),
	METHOD_NOT_ALLOWED(505, "不支持当前请求类型"),

	INVALID_ID(400, "无效ID"),
	UUID_CANNOT_BE_BLANK(400, "UUID不能为空"),
	BAD_REQUEST(400, "错误的请求"),
	UNAUTHORIZED(401, "未经授权"),
	NOT_FOUND(404, "没有找到资源"),


	;

    private final Integer value;
    private final String label;

}
