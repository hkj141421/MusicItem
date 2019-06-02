package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class Fans {
    private Long userid;

    private Long likeuserid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getLikeuserid() {
        return likeuserid;
    }

    public void setLikeuserid(Long likeuserid) {
        this.likeuserid = likeuserid;
    }

    public Fans(Long userid, Long likeuserid) {
        this.userid = userid;
        this.likeuserid = likeuserid;
    }

    public Fans() {
    }
}