package com.example.demo.Entity;

/**
 * Created by forget on 2019/3/13.
 */
public class CustomException extends RuntimeException {

    private final String errCode;
    private final String errMsg;

    public CustomException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
