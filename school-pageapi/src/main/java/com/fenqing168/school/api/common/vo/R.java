package com.fenqing168.school.api.common.vo;


/**
 * 返回数据
 */
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    public R(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R ok(String msg, T data){
        return new R(0, msg, data);
    }

    public static R ok(String msg){
        return new R(0, msg, null);
    }

    public static <T> R ok(T data){
        return new R(0, "ok", data);
    }

    public static R error(String msg){
        return new R(500, msg, null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
