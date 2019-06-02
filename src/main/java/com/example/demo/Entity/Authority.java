package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class Authority {
    private Long roleid;

    private String rolename;

    private String authorityname;

    private String authorityinfo;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getAuthorityname() {
        return authorityname;
    }

    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname == null ? null : authorityname.trim();
    }

    public String getAuthorityinfo() {
        return authorityinfo;
    }

    public void setAuthorityinfo(String authorityinfo) {
        this.authorityinfo = authorityinfo == null ? null : authorityinfo.trim();
    }
}