package com.example.demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by forget on 2018/11/23.
 */
@Component
public class AttachmentMail {
    @Autowired
    private SimpleMail mail;
    private String filepath;

    public AttachmentMail(SimpleMail mail) {
        this.mail = mail;
    }

    public AttachmentMail() {
        this.mail = new SimpleMail();
    }

    public String getFile() {
        return filepath;
    }

    public void setFile(String file) {
        this.filepath = file;
    }

    public SimpleMail getMail() {
        return mail;
    }

    public void setMail(SimpleMail mail) {
        this.mail = mail;
    }
}
