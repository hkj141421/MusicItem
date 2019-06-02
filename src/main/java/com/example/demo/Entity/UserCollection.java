package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class UserCollection {
    private Long userid;

    private Long sheetid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
    }

    public UserCollection(Long userid, Long sheetid) {
        this.userid = userid;
        this.sheetid = sheetid;
    }

    public UserCollection() {
    }
}