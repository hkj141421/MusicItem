package com.example.demo.Entity;

import org.springframework.stereotype.Component;

@Component
public class SongSheetInfoKey {
    private Long musicid;

    private Long sheetid;

    public Long getMusicid() {
        return musicid;
    }

    public void setMusicid(Long musicid) {
        this.musicid = musicid;
    }

    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
    }
}