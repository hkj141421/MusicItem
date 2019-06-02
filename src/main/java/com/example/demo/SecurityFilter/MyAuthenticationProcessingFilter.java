package com.example.demo.SecurityFilter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by forget on 2019/3/18.
 */
public class MyAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public MyAuthenticationProcessingFilter() {
        //该过滤器会拦截哪些路径和类型的请求
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 该方法是实现将用户登录信息从request提取，并封装成一个未认证的token传给AuthenticationManager处理
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        return null;
    }
}
