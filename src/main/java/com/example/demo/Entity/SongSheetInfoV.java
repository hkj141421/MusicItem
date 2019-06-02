package com.example.demo.Entity;

import org.springframework.stereotype.Component;

/**
 * Created by forget on 2018/12/11.
 */
@Component
public class SongSheetInfoV {/*歌单类*/

    private Long UserId;

    private Long SheetId;

    private String SongSheetName;

    private String Type;

    private String CoverImg;

    private String Producer;

    private String Introduce;

    private String State;

    private String Count;

    private String Time;

    public Long getSheetId() {
        return SheetId;
    }

    public void setSheetId(Long sheetId) {
        SheetId = sheetId;
    }

    public String getSongSheetName() {
        return SongSheetName;
    }

    public void setSongSheetName(String songSheetName) {
        SongSheetName = songSheetName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCoverImg() {
        return CoverImg;
    }

    public void setCoverImg(String coverImg) {
        CoverImg = coverImg;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    @Override
    public String toString() {
        return "SongSheetInfo{" +
                "UserId=" + UserId +
                ", SheetId=" + SheetId +
                ", SongSheetName='" + SongSheetName + '\'' +
                ", Type='" + Type + '\'' +
                ", CoverImg='" + CoverImg + '\'' +
                ", Producer='" + Producer + '\'' +
                ", Introduce='" + Introduce + '\'' +
                ", State='" + State + '\'' +
                ", Count='" + Count + '\'' +
                ", Time='" + Time + '\'' +
                '}';
    }
}
