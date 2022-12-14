package top.yinn.core.model;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.enums.BaseEnum;
import top.yinn.core.exception.code.ExceptionCode;

import java.io.Serializable;


/**
 * @author Yinn
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResult<T> implements Serializable {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "承载数据")
    private T data;

    public static <T> ApiResult<T> success() {
        return build(ExceptionCode.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(String msg) {
        return build(ExceptionCode.SUCCESS.getValue(), msg, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return build(ExceptionCode.SUCCESS, data);
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        return build(code, msg, null);
    }

    public static <T> ApiResult<T> fail(BaseEnum<Integer> enumItem) {
        return build(enumItem.getValue(), enumItem.getLabel(), null);
    }

    public static <T> ApiResult<T> fail(BaseEnum<Integer> enumItem, T data) {
        return build(enumItem.getValue(), enumItem.getLabel(), data);
    }

    public static <T> ApiResult<T> fail(Integer code, String msg, T data) {
        return build(code, msg, data);
    }

    public static <T> ApiResult<T> data(T data) {
        return build(HttpStatus.HTTP_OK, ObjectUtil.isEmpty(data) ? YinnConstant.Message.NULL : YinnConstant.Message.SUCCESS, data);
    }

    public static <T> ApiResult<T> data(String msg, T data) {
        return build(HttpStatus.HTTP_OK, msg, data);
    }

    public static <T> ApiResult<T> build(BaseEnum<Integer> enumItem) {
        return build(enumItem.getValue(), enumItem.getLabel(), null);
    }

    public static <T> ApiResult<T> build(BaseEnum<Integer> enumItem, T data) {
        return build(enumItem.getValue(), enumItem.getLabel(), data);
    }

    private static <T> ApiResult<T> build(Integer code, String msg, T data) {
        return (ApiResult<T>) ApiResult.builder().code(code).msg(msg).data(data).build();
    }

    
}
