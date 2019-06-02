package com.example.demo.SecurityHandler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.AjaxResponseBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败时调用此处理器返回结果
 * Created by forget on 2019/3/6.
 */
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        String msg = "{\"Auth\":\"Fail\"}";
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody("233", "认证失败", null);
//        JSONObject jsonObject = JSONObject.parseObject(msg);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ajaxResponseBody));
    }
}
