package top.yinn.core.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Yinn
 */
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
//@ResponseBody
@Slf4j
public abstract class DefaultGlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ApiResult<String> bizException(BizException ex, HttpServletRequest request) {
        log.warn("BizException:", ex);
        return ApiResult.fail(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, "Could not read document:")) {
            String msg = String.format("?????????????????????json??????????????????%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return ApiResult.fail(ExceptionCode.PARAM_EX.getValue(), msg);
        }
        return ApiResult.fail(ExceptionCode.PARAM_EX);
    }

    @ExceptionHandler(BindException.class)
    public ApiResult bindException(BindException ex, HttpServletRequest request) {
        log.warn("BindException:", ex);
        try {
            String msgs = ex.getBindingResult().getFieldError().getDefaultMessage();
            if (StrUtil.isNotEmpty(msgs)) {
                return ApiResult.fail(ExceptionCode.PARAM_EX.getValue(), msgs);
            }
        } catch (Exception ee) {
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("??????:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]????????????:[").append(oe.getRejectedValue()).append("]?????????????????????????????????.")
        );
        return ApiResult.fail(ExceptionCode.PARAM_EX.getValue(), msg.toString());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("?????????[").append(eee.getName())
                .append("]???????????????[").append(eee.getValue())
                .append("]???????????????????????????[").append(eee.getRequiredType().getName()).append("]?????????");
        return ApiResult.fail(ExceptionCode.PARAM_EX.getValue(), msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ApiResult illegalStateException(IllegalStateException ex, HttpServletRequest request) {
        log.warn("IllegalStateException:", ex);
        return ApiResult.fail(ExceptionCode.ILLEGALA_ARGUMENT_EX);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult missingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("MissingServletRequestParameterException:", ex);
        StringBuilder msg = new StringBuilder();
        msg.append("???????????????[").append(ex.getParameterType()).append("]???????????????[").append(ex.getParameterName()).append("]");
        return ApiResult.fail(ExceptionCode.ILLEGALA_ARGUMENT_EX.getValue(), msg.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public ApiResult nullPointerException(NullPointerException ex, HttpServletRequest request) {
        log.warn("NullPointerException:", ex);
        return ApiResult.fail(ExceptionCode.NULL_POINT_EX);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.warn("IllegalArgumentException:", ex);
        return ApiResult.fail(ExceptionCode.ILLEGALA_ARGUMENT_EX);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("????????????(Content-Type)[").append(contentType.toString()).append("] ???????????????????????????????????????");
            return ApiResult.fail(ExceptionCode.MEDIA_TYPE_EX.getValue(), msg.toString());
        }
        return ApiResult.fail(ExceptionCode.MEDIA_TYPE_EX.getValue(), "?????????Content-Type??????");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ApiResult missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        log.warn("MissingServletRequestPartException:", ex);
        return ApiResult.fail(ExceptionCode.REQUIRED_FILE_PARAM_EX);
    }

    @ExceptionHandler(ServletException.class)
    public ApiResult servletException(ServletException ex, HttpServletRequest request) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return ApiResult.fail(ExceptionCode.REQUIRED_FILE_PARAM_EX);
        }
        return ApiResult.fail(ExceptionCode.SYSTEM_BUSY.getValue(), ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ApiResult multipartException(MultipartException ex, HttpServletRequest request) {
        log.warn("MultipartException:", ex);
        return ApiResult.fail(ExceptionCode.REQUIRED_FILE_PARAM_EX);
    }

    /**
     * jsr ????????????????????????
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult<String> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ApiResult.fail(ExceptionCode.BASE_VALID_PARAM.getValue(), message);
    }

    /**
     * spring ?????????????????????????????? ???conttoller????????????result?????????????????????
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn("MethodArgumentNotValidException:", ex);
        return ApiResult.fail(ExceptionCode.BASE_VALID_PARAM.getValue(), ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ????????????
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<String> otherExceptionHandler(Exception ex, HttpServletRequest request) {
        log.warn("Exception:", ex);
        if (ex.getCause() instanceof BizException) {
            return this.bizException((BizException) ex.getCause(), request);
        }
        return ApiResult.fail(ExceptionCode.SYSTEM_BUSY);
    }


    /**
     * ???????????????:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ApiResult<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return ApiResult.fail(ExceptionCode.METHOD_NOT_ALLOWED);
    }


    @ExceptionHandler(PersistenceException.class)
    public ApiResult<String> persistenceException(PersistenceException ex, HttpServletRequest request) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof BizException) {
            BizException cause = (BizException) ex.getCause();
            return ApiResult.fail(cause.getCode(), cause.getMessage());
        }
        return ApiResult.fail(ExceptionCode.SQL_EX);
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public ApiResult<String> myBatisSystemException(MyBatisSystemException ex, HttpServletRequest request) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof PersistenceException) {
            return this.persistenceException((PersistenceException) ex.getCause(), request);
        }
        return ApiResult.fail(ExceptionCode.SQL_EX);
    }

    @ExceptionHandler(SQLException.class)
    public ApiResult sqlException(SQLException ex, HttpServletRequest request) {
        log.warn("SQLException:", ex);
        return ApiResult.fail(ExceptionCode.SQL_EX);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResult dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        log.warn("DataIntegrityViolationException:", ex);
        return ApiResult.fail(ExceptionCode.SQL_EX);
    }

    @ExceptionHandler(JSONException.class)
    public ApiResult JSONException(JSONException ex, HttpServletRequest request) {
        log.warn("JSONException:", ex);
        return ApiResult.fail(ExceptionCode.PARAM_EX);
    }

    @ExceptionHandler({SaTokenException.class})
    public ApiResult saTokenException(SaTokenException ex, HttpServletRequest request) {
        log.warn("SaTokenException:", ex);
        return ApiResult.fail(ExceptionCode.UNAUTHORIZED, ex.getMessage());
    }

}
