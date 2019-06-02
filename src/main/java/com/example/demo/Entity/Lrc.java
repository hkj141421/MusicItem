package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class Lrc {
    private Long lrcid;

    private String lrcname;

    private String content;

    public Long getLrcid() {
        return lrcid;
    }

    public void setLrcid(Long lrcid) {
        this.lrcid = lrcid;
    }

    public String getLrcname() {
        return lrcname;
    }

    public void setLrcname(String lrcname) {
        this.lrcname = lrcname == null ? null : lrcname.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}