package com.example.demo.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SongSheet {
    private Long sheetid;

    private String songsheetname;

    private String type;

    private String coverimg;

    private String producer;

    private Integer state;

    private Date time;

    private String introduce;

    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
    }

    public String getSongsheetname() {
        return songsheetname;
    }

    public void setSongsheetname(String songsheetname) {
        this.songsheetname = songsheetname == null ? null : songsheetname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg == null ? null : coverimg.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}