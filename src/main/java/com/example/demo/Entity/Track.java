package com.example.demo.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Track {
    private Long trackid;

    private Long userid;

    private Date date;

    private String imgcontent1;

    private String imgcontent2;

    private String imgcontent3;

    private String videocontent;

    private Long fabulous;

    private String textcontent;

    public Long getTrackid() {
        return trackid;
    }

    public void setTrackid(Long trackid) {
        this.trackid = trackid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImgcontent1() {
        return imgcontent1;
    }

    public void setImgcontent1(String imgcontent1) {
        this.imgcontent1 = imgcontent1 == null ? null : imgcontent1.trim();
    }

    public String getImgcontent2() {
        return imgcontent2;
    }

    public void setImgcontent2(String imgcontent2) {
        this.imgcontent2 = imgcontent2 == null ? null : imgcontent2.trim();
    }

    public String getImgcontent3() {
        return imgcontent3;
    }

    public void setImgcontent3(String imgcontent3) {
        this.imgcontent3 = imgcontent3 == null ? null : imgcontent3.trim();
    }

    public String getVideocontent() {
        return videocontent;
    }

    public void setVideocontent(String videocontent) {
        this.videocontent = videocontent == null ? null : videocontent.trim();
    }

    public Long getFabulous() {
        return fabulous;
    }

    public void setFabulous(Long fabulous) {
        this.fabulous = fabulous;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }
}