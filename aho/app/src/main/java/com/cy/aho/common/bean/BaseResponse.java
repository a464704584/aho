package com.cy.aho.common.bean;

/**
 * @创建者 CY
 * @创建时间 2019/12/24 17:18
 * @描述
 */

public class BaseResponse<T> {
    private int code;
    private String msg;
    private T body;

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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
