package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class Singer {
    private Long singerid;

    private String singername;

    private String singerimg;

    private String singersex;

    private Long singerhotnumber;

    private String alias;

    private Double albumsize;

    private String briefdesc;

    public Long getSingerid() {
        return singerid;
    }

    public void setSingerid(Long singerid) {
        this.singerid = singerid;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername == null ? null : singername.trim();
    }

    public String getSingerimg() {
        return singerimg;
    }

    public void setSingerimg(String singerimg) {
        this.singerimg = singerimg == null ? null : singerimg.trim();
    }

    public String getSingersex() {
        return singersex;
    }

    public void setSingersex(String singersex) {
        this.singersex = singersex == null ? null : singersex.trim();
    }

    public Long getSingerhotnumber() {
        return singerhotnumber;
    }

    public void setSingerhotnumber(Long singerhotnumber) {
        this.singerhotnumber = singerhotnumber;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Double getAlbumsize() {
        return albumsize;
    }

    public void setAlbumsize(Double albumsize) {
        this.albumsize = albumsize;
    }

    public String getBriefdesc() {
        return briefdesc;
    }

    public void setBriefdesc(String briefdesc) {
        this.briefdesc = briefdesc == null ? null : briefdesc.trim();
    }
}