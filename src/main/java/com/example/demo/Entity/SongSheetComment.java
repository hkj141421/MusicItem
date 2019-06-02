package com.example.demo.Entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by forget on 2019/5/10.
 */
@Component
public class SongSheetComment {
    private Long commentid;

    private Long sheetid;

    private Long userid;

    private Date date;

    private Long fabulous;

    private Long replycommentid;

    private String content;

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public Long getSheetid() {
        return sheetid;
    }

    public void setSheetid(Long sheetid) {
        this.sheetid = sheetid;
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

    public Long getFabulous() {
        return fabulous;
    }

    public void setFabulous(Long fabulous) {
        this.fabulous = fabulous;
    }

    public Long getReplycommentid() {
        return replycommentid;
    }

    public void setReplycommentid(Long replycommentid) {
        this.replycommentid = replycommentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
