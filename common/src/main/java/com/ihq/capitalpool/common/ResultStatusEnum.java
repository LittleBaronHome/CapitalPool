package com.ihq.capitalpool.common;

public enum ResultStatusEnum {
    PasswordError(401, "Password Error", "用户名密码错误"),
    IllegalToken(401, "Illegal Token", "无效的Token"),
    ExpiredToken(401, "Expired Token", "过期的token"),
    IllegalSignature(401, "Illegal Signature", "无效的签名"),

    ParameterError(400, "Parameter Error", "参数错误"),

    RuntimeException(500, "Runtime Exception", "运行时异常"),
    UnknownException(500, "Unknown Exception", "未知异常"),
    BusinessException(501, "Business Exception", "业务异常")
    ;

    private Integer status;
    private String message;
    private String code;

    ResultStatusEnum(Integer status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
