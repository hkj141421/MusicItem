package com.example.demo.Entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by forget on 2019/3/6.
 */
@Component
public class AjaxResponseBody implements Serializable {
    private String status;
    private String msg;
    private Object data;

    public AjaxResponseBody(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public AjaxResponseBody() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
