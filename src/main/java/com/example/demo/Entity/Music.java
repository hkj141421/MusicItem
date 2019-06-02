package com.example.demo.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Music {
    private Long musicid;

    private String musicname;

    private String singer;

    private Long playnumber;

    private String time;

    private String type;

    private String album;

    private String language;

    private Long lyricid;

    private String musicaddress;

    private String musicimg;

    private Date creationdate;

    public Long getMusicid() {
        return musicid;
    }

    public void setMusicid(Long musicid) {
        this.musicid = musicid;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname == null ? null : musicname.trim();
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer == null ? null : singer.trim();
    }

    public Long getPlaynumber() {
        return playnumber;
    }

    public void setPlaynumber(Long playnumber) {
        this.playnumber = playnumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album == null ? null : album.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Long getLyricid() {
        return lyricid;
    }

    public void setLyricid(Long lyricid) {
        this.lyricid = lyricid;
    }

    public String getMusicaddress() {
        return musicaddress;
    }

    public void setMusicaddress(String musicaddress) {
        this.musicaddress = musicaddress == null ? null : musicaddress.trim();
    }

    public String getMusicimg() {
        return musicimg;
    }

    public void setMusicimg(String musicimg) {
        this.musicimg = musicimg == null ? null : musicimg.trim();
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }
}