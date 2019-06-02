package com.example.demo.SecurityHandler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.SecurityUser;
import com.example.demo.Service.MyCustomUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功是调用此处理器返回结果
 * Created by forget on 2019/3/6.
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private MyCustomUserService myCustomUserService;

    MyAuthenticationSuccessHandler(MyCustomUserService myCustomUserService) {
        this.myCustomUserService = myCustomUserService;
    }

    public MyAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
//            String token=myCustomUserService.CreateUserInfoToken((UserDetails) authentication.getPrincipal());
//            httpServletResponse.setHeader("Authorization",token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AjaxResponseBody ajaxResponseBody = null;
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        securityUser.setPassword("");
        securityUser.getUser().setUserpassword("");
        if (securityUser.getUser().getStatus().equals("1")) {
            ajaxResponseBody = new AjaxResponseBody("205", "账号已被冻结", null);
        } else ajaxResponseBody = new AjaxResponseBody("200", "success", securityUser);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ajaxResponseBody));
    }
}
