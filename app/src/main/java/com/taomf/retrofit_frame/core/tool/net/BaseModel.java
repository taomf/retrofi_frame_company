package com.taomf.retrofit_frame.core.tool.net;

/**
 * 备注：HTTP请求返回Model
 */
public class BaseModel<T> {

    public static final int STATE_SUCCESS = 200;


    /**0成功   1失败   -1异常*/
    private int code;

    private String msg;

    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}