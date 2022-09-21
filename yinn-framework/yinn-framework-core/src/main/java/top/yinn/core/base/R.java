package top.yinn.core.base;

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
 *
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class R<T> implements Serializable {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "承载数据")
    private T data;

    public static <T> R<T> success() {
        return build(ExceptionCode.SUCCESS, null);
    }

    public static <T> R<T> success(String msg) {
        return build(ExceptionCode.SUCCESS.getValue(), msg, null);
    }

    public static <T> R<T> success(T data) {
        return build(ExceptionCode.SUCCESS, data);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return build(code, msg, null);
    }

    public static <T> R<T> fail(BaseEnum<Integer> enumItem) {
        return build(enumItem.getValue(), enumItem.getLabel(), null);
    }

    public static <T> R<T> fail(BaseEnum<Integer> enumItem, T data) {
        return build(enumItem.getValue(), enumItem.getLabel(), data);
    }

    public static <T> R<T> fail(Integer code, String msg, T data) {
        return build(code, msg, data);
    }

    public static <T> R<T> data(T data) {
        return build(HttpStatus.HTTP_OK, ObjectUtil.isEmpty(data) ? YinnConstant.Message.NULL : YinnConstant.Message.SUCCESS, data);
    }

    public static <T> R<T> data(String msg, T data) {
        return build(HttpStatus.HTTP_OK, msg, data);
    }

    public static <T> R<T> build(BaseEnum<Integer> enumItem) {
        return build(enumItem.getValue(), enumItem.getLabel(), null);
    }

    public static <T> R<T> build(BaseEnum<Integer> enumItem, T data) {
        return build(enumItem.getValue(), enumItem.getLabel(), data);
    }

    private static <T> R<T> build(Integer code, String msg, T data) {
        return (R<T>) R.builder().code(code).msg(msg).data(data).build();
    }


    public boolean isSuccess() {
        return this.getCode() == ExceptionCode.SUCCESS.getValue();
    }
}
