package com.lancoo.global.exception;

public class MyRuntimeException extends RuntimeException{
    private Integer code;
    private Object data;

    public MyRuntimeException(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public MyRuntimeException(String message, Integer code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public MyRuntimeException(String message, Throwable cause, Integer code, Object data) {
        super(message, cause);
        this.code = code;
        this.data = data;
    }

    public MyRuntimeException(Throwable cause, Integer code, Object data) {
        super(cause);
        this.code = code;
        this.data = data;
    }

    public MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, Object data) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
