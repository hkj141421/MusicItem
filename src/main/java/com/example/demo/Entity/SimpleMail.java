package com.example.demo.Entity;

import org.springframework.stereotype.Component;

/**
 * Created by forget on 2018/11/22.
 */
@Component
public class SimpleMail {
    private String To;
    private String subject;
    private String TextContent;

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextContent() {
        return TextContent;
    }

    public void setTextContent(String textContent) {
        TextContent = textContent;
    }
}
