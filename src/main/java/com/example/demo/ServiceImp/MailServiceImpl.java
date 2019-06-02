package com.example.demo.ServiceImp;

import com.example.demo.Entity.AttachmentMail;
import com.example.demo.Entity.SimpleMail;
import com.example.demo.Service.MailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by forget on 2018/11/16.
 */
@Service
public class MailServiceImpl implements MailService {

    private static JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private String from = "670378784@qq.com";

    static {
        mailSender.setHost("smtp.qq.com");
        mailSender.setUsername("670378784@qq.com");
        mailSender.setPassword("nypuslzeifbnbfad");
        mailSender.setDefaultEncoding("UTF-8");
    }


    @Override
    public String sendSimpleMail(SimpleMail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getTextContent());
        message.setFrom(from);

        try {
            mailSender.send(message);
            return "Success";
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！" + e);
            e.printStackTrace();
        } finally {

        }
        return "fail";
    }

    @Override
    public String sendHtmlMail(SimpleMail mail) throws MessagingException {
        return this.sendSimpleMail(mail);
    }

    @Override
    public String sendAttachmentsMail(AttachmentMail attmail) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.addTo(attmail.getMail().getTo());
            helper.setFrom(from);
            helper.setSubject(attmail.getMail().getSubject());
            helper.setText(attmail.getMail().getTextContent(), true);
            FileSystemResource file = new FileSystemResource(new File(attmail.getFile()));
            System.out.println(file.getFilename());
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
            System.out.println("附件邮件发送成功");
            return "Success";
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("附件邮件发送失败" + e);
            return "Fail";
        }
    }
}