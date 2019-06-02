package com.example.demo.SecurityHandler;

import com.example.demo.Entity.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出时调用此处理器
 * Created by forget on 2019/3/8.
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            httpServletResponse.getWriter().write(securityUser.getUsername() + "logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(securityUser.getUsername() + "----logout");
    }
}
