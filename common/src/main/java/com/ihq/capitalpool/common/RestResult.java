package com.ihq.capitalpool.common;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;

public class RestResult {
    private Boolean result = true;
    private Integer status = 200;
    private Object data;
    private String message;
    private String code;

    public RestResult() {
    }

    public RestResult(Object data) {
        this.data = data;
    }

    public RestResult(Boolean result, Integer status, String code, String message) {
        this.result = result;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public RestResult(ResultStatusEnum resultStatusEnum) {
        this.result = false;
        this.status = resultStatusEnum.getStatus();
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }

    public RestResult(ResultStatusEnum resultStatusEnum, String message) {
        this.result = false;
        this.status = resultStatusEnum.getStatus();
        this.code = resultStatusEnum.getCode();
        this.message = message;
    }

    public RestResult(ResultStatusEnum resultStatusEnum, String code, String message) {
        this.result = false;
        this.status = resultStatusEnum.getStatus();
        this.code = code;
        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public HashMap getHashMapData() {
       return JSONObject.parseObject(JSONObject.toJSONString(this.data), HashMap.class);
    }
}
