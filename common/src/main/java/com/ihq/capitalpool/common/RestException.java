package com.ihq.capitalpool.common;

public class RestException extends RuntimeException {
    private int status = 501;
    private Object data;
    private String code;

    public RestException() {
    }



    public RestException(String message) {
        super(message);
    }

    public RestException(RestResult restResult) {
        this(restResult.getMessage());
        this.code = restResult.getCode();
        this.data = restResult.getData();
        this.status = restResult.getStatus();
    }

    public RestException(String message, String code) {
        this(message);
        this.code = code;
    }

    public RestException(int status, String message, Object data) {
        this(message);
        this.data = data;
        this.status = status;
    }

    public RestException(int status, String code, String message, Object data) {
        this(message);
        this.code = code;
        this.data = data;
        this.status = status;
    }

    public RestException(ResultStatusEnum resultStatusEnum) {
        this(resultStatusEnum.getMessage());
        this.code = resultStatusEnum.getCode();
        this.status = resultStatusEnum.getStatus();
    }

    public RestException(ResultStatusEnum resultStatusEnum, String code, String message) {
        this(message);
        this.code = code;
        this.status = resultStatusEnum.getStatus();
    }

    public RestException(ResultStatusEnum resultStatusEnum, String message) {
        this(message);
        this.code = resultStatusEnum.getCode();
        this.status = resultStatusEnum.getStatus();
    }

    public RestException(ResultStatusEnum resultStatusEnum, String code, String message, Object data) {
        this(message);
        this.code = code;
        this.status = resultStatusEnum.getStatus();
        this.data = data;
    }

    public RestException(ResultStatusEnum resultStatusEnum, String message, Object data) {
        this(message);
        this.code = resultStatusEnum.getCode();
        this.status = resultStatusEnum.getStatus();
        this.data = data;
    }

    public RestException(String message, int status) {
        super(message);
        this.status = status;
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
