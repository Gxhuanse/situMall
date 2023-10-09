package com.gxh.common;

/**
 *公共返回对象
 * @author admin
 */
public class ResponseBean<T> {
    // 返回请求代码
    private Integer code;
    // 返回数据
    private T res;
    // 返回提示信息
    private String msg;


    private ResponseBean(Integer status) {
        this.code = status;
    }


    private ResponseBean(Integer status, String msg) {
        this.code = status;
        this.msg = msg;
    }

    private ResponseBean(Integer status, String msg, T res) {
        this.code = status;
        this.msg = msg;
        this.res = res;
    }

    public ResponseBean() {

    }

    public static <T> ResponseBean<T> ok() {
        return new ResponseBean<>(200);
    }

    public static <T> ResponseBean<T> ok(T body) {
        return ok(200, body);
    }

    public static <T> ResponseBean<T> ok(String msg, T body) {
        return ok(200, msg, body);
    }


    public static <T> ResponseBean<T> ok(Integer status, T body) {
        return new ResponseBean<>(status, (String) null, body);
    }

    public static <T> ResponseBean<T> ok(Integer status, String msg, T body) {
        return new ResponseBean<>(status, msg, body);
    }

    public static <T> ResponseBean<T> failed(String msg) {
        return new ResponseBean<>(500, msg);
    }

    public static <T> ResponseBean<T> failed(Integer status, String msg, T body) {
        return new ResponseBean<>(status, msg, body);
    }

    public static <T> ResponseBean<T> failed(Integer status) {
        return new ResponseBean<>(status);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseBean{code=" + this.code + ", res=" + this.res + ", msg='" + this.msg + "'}";
    }
}
