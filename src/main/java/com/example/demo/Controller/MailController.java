package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.AttachmentMail;
import com.example.demo.Entity.SimpleMail;
import com.example.demo.Service.MailService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by forget on 2018/11/22.
 */
@RestController
public class MailController {
    @Autowired
    MailService mservice;
    @Autowired
    UserService userService;

    @GetMapping("/api/email/verificationcode")
    public AjaxResponseBody sendCodemail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        if (!userService.IsExistEmail(email)) {
            HttpSession session = request.getSession();
            SimpleMail mail = new SimpleMail();

            mail.setTo(email);
            mail.setSubject("时代音乐验证码");
            int num = (int) (Math.random() * 1000000) / 1;
            mail.setTextContent("验证码为：" + num);
            String str = mservice.sendSimpleMail(mail);
            session.setAttribute("email", email);
            session.setAttribute("emailcode", num);
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "邮箱已存在", null);
        }


    }

    @PostMapping("/api/sendmail")
    public AjaxResponseBody sendmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SimpleMail mail = new SimpleMail();
        mail.setTo(request.getParameter("To"));
        mail.setSubject(request.getParameter("subject"));
        mail.setTextContent(request.getParameter("content"));
        String str = mservice.sendSimpleMail(mail);
        return new AjaxResponseBody("200", "success", null);
    }

    @PostMapping("/api/sendAttmail")
    public AjaxResponseBody sendAttmail(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws IOException, MessagingException {
        AttachmentMail mail = new AttachmentMail();
        mail.getMail().setTo(request.getParameter("To"));
        mail.getMail().setSubject(request.getParameter("subject"));
        mail.getMail().setTextContent(request.getParameter("content"));
        response.setCharacterEncoding("UTF-8");
        if (!file.isEmpty() && file.getSize() < 50 * 1024 * 1024) {
            String filepath = "C:\\Users\\forget\\Desktop\\" + file.getOriginalFilename();
            File up_file = new File(filepath);
            file.transferTo(up_file);
            mail.setFile(filepath);
            String str = mservice.sendAttachmentsMail(mail);
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("500", "fail", null);
        }
    }
}
