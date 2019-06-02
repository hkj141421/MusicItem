package com.example.demo.SecurityHandler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功时调用此处理器
 * Created by forget on 2019/3/8.
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody("200", "success", null);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ajaxResponseBody));
    }
}
