package com.git.bds.nyc.framework.web.handler;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/8/14 17:25
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    public R<String> exceptionHandler(Exception e){
        log.error("未知异常,原因是:",e);
        return R.error(ResultCode.INTERNAL_SERVER_ERROR.getCode(),ResultCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 路径错误
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public R<String> noHandlerFoundException(Exception e){
        log.error("未知异常,原因是:",e);
        return R.error(ResultCode.PAGE_NOT_FOUND.getCode(),ResultCode.PAGE_NOT_FOUND.getMessage());
    }


    /**
     * 处理 SpringMVC 请求参数类型错误
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return R.error(ResultCode.BAD_REQUEST.getCode(), String.format("请求参数类型错误:%s", ex.getMessage()));
    }

    /**
     * 处理 SpringMVC 请求方法不正确
     * 例如说，A 接口的方法为 GET 方式，结果请求方法为 POST 方式，导致不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.warn("[httpRequestMethodNotSupportedExceptionHandler]", ex);
        return R.error(ResultCode.METHOD_NOT_ALLOWED.getCode(), String.format("请求方法不正确:%s", ex.getMessage()));
    }

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public R<String> baseExceptionHandler(BusinessException e){
        log.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return R.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    public R<String> exceptionHandler(NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return R.error(ResultCode.BODY_NOT_MATCH.getCode(), ResultCode.BODY_NOT_MATCH.getMessage());
    }


    /**
     * 处理校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R<String> constraintViolationExceptionHandler(ConstraintViolationException e){
        log.error("未知异常！原因是:",e);
        return R.error(ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getCode(), ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getMessage());
    }

    /**
     * 处理请求参数异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.error("未知异常！原因是:",e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return R.error(ResultCode.METHOD_ARGUMENT_NOT_VALID.getCode(),fieldError.getDefaultMessage());
            }
        }
        return R.error(ResultCode.METHOD_ARGUMENT_NOT_VALID.getCode(),ResultCode.METHOD_ARGUMENT_NOT_VALID.getMessage());
    }



    /**
     * 处理请求参数异常
     * 处理 SpringMVC 请求参数缺失
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public R<String> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        log.error("未知异常！原因是:",e);
        return R.error(ResultCode.REQUEST_PARAMETER_EXCEPTION.getCode(), ResultCode.REQUEST_PARAMETER_EXCEPTION.getMessage());
    }

    /*Sa-token 的一些异常处理*/

    @ExceptionHandler(NotLoginException.class)
    public R<String> handlerNotLoginException(NotLoginException nle) {
        // 不同异常返回不同状态码
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供Token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "未提供有效的Token";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "登录信息已过期，请重新登录";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "您的账户已在另一台设备上登录，如非本人操作，请立即修改密码";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "已被系统强制下线";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return R.error(401, message);
    }

    /**
     * 处理角色异常
     */
    @ExceptionHandler(value = NotRoleException.class)
    public R<String> notRoleExceptionHandler(NotRoleException e){
        return R.error(ResultCode.NOT_ROLE.getCode(), ResultCode.NOT_ROLE.getMessage());
    }

    /**
     * 处理权限异常
     */
    @ExceptionHandler(value = NotPermissionException.class)
    public R<String> notPermissionExceptionHandler(NotPermissionException e){
        return R.error(ResultCode.NOT_PERMISSION.getCode(), ResultCode.NOT_PERMISSION.getMessage());
    }

    /**
     * 账号被封禁
     */
    @ExceptionHandler(value = DisableLoginException.class)
    public R<String> disableLoginExceptionHandler(DisableLoginException e){
        return R.error(ResultCode.DIS_LOGIN.getCode(), ResultCode.DIS_LOGIN.getMessage());
    }




}
