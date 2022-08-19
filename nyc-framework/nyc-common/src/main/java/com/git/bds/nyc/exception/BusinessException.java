package com.git.bds.nyc.exception;

import com.git.bds.nyc.result.ResultCode;

/**
 * @author 成大事
 * @since 2022/8/14 17:23
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    /**
     * 错误状态码
     */
    protected Integer errorCode;
    /**
     * 错误提示
     */
    protected String errorMsg;

    public BusinessException(){

    }

    public BusinessException(String errorMsg){
        this.errorCode = ResultCode.WX_ERROR.getCode();
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
