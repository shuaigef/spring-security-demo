package com.shuaigef.springsecuritydemo.common.code;

/**
 * 自定义错误码
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public enum ErrorCode {

    SUCCESS(0, "操作成功"),

    BAD_REQUEST(40000, "异常请求"),
    PARAMS_ERROR(40001, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    FORBIDDEN_ERROR(40300, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),

    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败");



    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
