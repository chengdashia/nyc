package com.git.bds.nyc.result;

import lombok.Getter;

/**
 * @author 成大事
 * @since 2022/6/2 11:12
 */
public enum ResultCode {
    /**操作失败**/
    SERVICE_UNAVAILABLE(501,"系统维护,暂时无法处理客户端请求。"),

    /**操作成功**/
    SUCCESS(200,"操作成功！"),
    /**密码错误**/
    PWD_ERROR(411,"密码错误"),
    /**不存在**/
    NOT_EXIST(412,"不存在"),
    /**验证码不正确**/
    CAPTCHA_ERROR(413,"验证码不正确"),
    /**验证码过期**/
    CAPTCHA_EXPIRE(414,"验证码过期"),
    /**已注册**/
    REGISTERED(415,"该用户已注册"),
    /** 格式不匹配**/
    FORMAT_MISMATCH(416,"格式不匹配！请检查输入的手机号或邮箱是否合规！"),
    /**服务异常**/
    FAILED(500,"系统异常，请稍后重试"),
    /**请求的数据格式不符**/
    BODY_NOT_MATCH(4000,"请求的数据格式不符!"),
    /**服务器内部错误**/
    INTERNAL_SERVER_ERROR(5000, "服务器内部错误!"),
    /**参数格式不合规**/
    CONSTRAINT_VIOLATION_EXCEPTION(5002, "参数格式不合规!"),
    /**请求参数不合规**/
    REQUEST_PARAMETER_EXCEPTION(5003, "请求参数不合规!"),
    /**登录过期。或token无效！**/
    NOT_LOGIN(5004, "登录过期。或token无效！"),
    /** 角色异常 **/
    NOT_ROLE(5005, "角色异常！"),
    /**权限不够**/
    NOT_PERMISSION(5006, "权限不够！"),
    /**权限不够**/
    DIS_LOGIN(5007, "账号被封禁了！"),
    /**服务器正忙，请稍后再试!**/
    SERVER_BUSY(5008,"服务器正忙，请稍后再试!");


    /**自定义状态码**/
    @Getter
    private final Integer code;

    /**
     * 携 带 消 息
     */
    @Getter
    private final String message;
    /**
     * 构 造 方 法
     */
    ResultCode(Integer code, String message) {

        this.code = code;

        this.message = message;
    }

}
