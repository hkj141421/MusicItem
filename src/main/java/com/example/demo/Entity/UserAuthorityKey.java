package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class UserAuthorityKey {
    private Long userid;

    private Long roleid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}