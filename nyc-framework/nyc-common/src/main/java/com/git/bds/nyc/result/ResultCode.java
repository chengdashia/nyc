package com.git.bds.nyc.result;

import lombok.Getter;

/**
 * @author 成大事
 * @since 2022/6/2 11:12
 */
public enum ResultCode {
    /** code：2xx **/
    /**操作成功**/
    SUCCESS(200,"操作成功！"),

    /** code：4xx **/
    /** 客户端错误 **/

    /**请求的数据格式不符**/
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    BAD_REQUEST(400,"请求参数不正确"),
    METHOD_NOT_ALLOWED(400,"请求参数不正确"),
    /** 方法参数无效 */
    METHOD_ARGUMENT_NOT_VALID(400, "方法参数无效!"),
    /**参数格式不合规**/
    CONSTRAINT_VIOLATION_EXCEPTION(400, "参数格式不合规!"),
    /**请求参数不合规**/
    REQUEST_PARAMETER_EXCEPTION(400, "请求参数不合规!"),
    /**微信登录失败**/
    WX_ERROR(401, "微信授权登录出错"),
    /**密码错误**/
    PWD_ERROR(401,"密码错误"),
    /**不存在**/
    NOT_EXIST(401,"不存在"),
    /** 收集 */
    COLLECTED(401,"已收藏！"),
    /**登录过期。或token无效！**/
    NOT_LOGIN(401, "登录过期。或token无效！"),
    /** 角色异常 **/
    NOT_ROLE(401, "角色异常！"),
    /**权限不够**/
    NOT_PERMISSION(401, "权限不够！"),
    /**权限不够**/
    DIS_LOGIN(401, "账号被封禁了！"),
    /**已注册**/
    REGISTERED(403,"该用户已注册"),
    NOT_FOUND(404,"请求不存在！！！"),
    PAGE_NOT_FOUND(404,"未找到页面！"),
    /**验证码不正确**/
    CAPTCHA_ERROR(412,"验证码不正确"),
    /**验证码过期**/
    CAPTCHA_EXPIRE(412,"验证码过期"),
    /** 格式不匹配**/
    FORMAT_MISMATCH(412,"格式不匹配！请检查输入的手机号或邮箱是否合规！"),
    /** 文件类型错误 */
    FILE_TYPE_ERROR(415,"文件格式有误！请确认后上传！！！"),

    /** code：5xx **/
    /**服务器错误**/

    /**操作失败**/
    SERVICE_UNAVAILABLE(500,"系统维护,暂时无法处理客户端请求。"),
    /**服务异常**/
    FAILED(500,"系统异常，请稍后重试"),
    /**服务器内部错误**/
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    /**服务器正忙，请稍后再试!**/
    SERVER_BUSY(504,"服务器正忙，请稍后再试!");



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
