package com.ihq.capitalpool.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RestExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public
    @ResponseBody
    RestResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        RestResult result = new RestResult(ResultStatusEnum.ParameterError, e.getMessage());
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public
    @ResponseBody
    RestResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : allErrors) {
            stringBuilder.append(error.getDefaultMessage()).append(",");
        }
        RestResult result = new RestResult(ResultStatusEnum.ParameterError, stringBuilder.toString());
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(RestException.class)
    public
    @ResponseBody
    RestResult handleRestException(RestException e, HttpServletRequest request) {
        RestResult result = new RestResult(false, e.getStatus(), e.getCode(), e.getMessage());
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    public
    @ResponseBody
    RestResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        RestResult result = new RestResult(ResultStatusEnum.RuntimeException, e.getMessage());
        e.printStackTrace();
        return result;
    }

    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    RestResult handleException(Exception e, HttpServletRequest request) {
        RestResult result = new RestResult(ResultStatusEnum.UnknownException, e.getMessage());
        e.printStackTrace();
        return result;
    }
}
