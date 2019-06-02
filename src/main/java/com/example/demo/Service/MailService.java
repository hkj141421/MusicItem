package com.example.demo.Service;

import com.example.demo.Entity.AttachmentMail;
import com.example.demo.Entity.SimpleMail;

import javax.mail.MessagingException;

/**
 * Created by forget on 2018/11/16.
 */
public interface MailService {

    String sendSimpleMail(SimpleMail mail);

    String sendHtmlMail(SimpleMail mail) throws MessagingException;

    String sendAttachmentsMail(AttachmentMail attmail) throws MessagingException;
}
