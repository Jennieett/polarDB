package com.example.demo.common;

import com.example.demo.common.Constants;
import java.io.Serializable;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS;
    public static final int FAIL;
    private int code;
    private String msg;
    private T data;

    public R() {
    }

    public static <T> R<T> ok() {
        return (R<T>) restResult((Object)null, SUCCESS, (String)null);
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, (String)null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return (R<T>) restResult((Object)null, FAIL, (String)null);
    }

    public static <T> R<T> fail(String msg) {
        return (R<T>) restResult((Object)null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, (String)null);
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return (R<T>) restResult((Object)null, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    static {
        SUCCESS = Constants.SUCCESS;
        FAIL = Constants.FAIL;
    }
}
