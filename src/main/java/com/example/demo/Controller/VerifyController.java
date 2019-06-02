package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.User;
import com.example.demo.Service.UserService;
import com.example.demo.Util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by forget on 2019/5/19.
 */
@RestController
public class VerifyController {
    @Autowired
    UserService userService;

    @GetMapping("/api/VerifyCode")
    public AjaxResponseBody getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String[] str = new String[2];
            String VerificationCode = (String) request.getSession().getAttribute("VerificationCode");
            request.getSession().removeAttribute("VerificationCode");
            str[0] = "data:image/jpeg;base64," + VerificationCodeUtil.generateVerificationCode(request);
            str[1] = VerificationCode;
            return new AjaxResponseBody("200", "success", str);
        } catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage(), null);
        }
    }

    @PostMapping("/api/VerifyCode")
    public AjaxResponseBody checkVerifyCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String Code) {
        String VerificationCode = (String) request.getSession().getAttribute("VerificationCode");
        request.getSession().removeAttribute("VerificationCode");

        if (Code.equals(VerificationCode)) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "验证失败", null);
        }
    }

    @GetMapping("/api/Verify/username")
    public AjaxResponseBody checkUserName(@RequestParam("username") String username) {
        //验证用户名
        System.out.println(username);
        User user = userService.getUserByName(username);
        if (user != null) {
            return new AjaxResponseBody("233", "用户名已存在", null);
        } else {
            return new AjaxResponseBody("200", "用户名可用", null);
        }
    }

    @GetMapping("/api/Verify/account")
    public AjaxResponseBody checkAccount(@RequestParam("account") String account) {
        //验证账号是否存在
        User user = userService.getUserByAccount(account);
        if (user != null) {
            return new AjaxResponseBody("233", "账号已存在", null);
        } else {
            return new AjaxResponseBody("200", "账号可用", null);
        }
    }
}
