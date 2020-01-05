package com.example.demo.SecurityFilter;

import com.example.demo.Entity.JwtAuthenticationToken;
import com.example.demo.Service.MyCustomUserService;
import com.example.demo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by forget on 2019/3/20.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private RequestMatcher requiresAuthenticationRequestMatcher;
    private List<RequestMatcher> permissiveRequestMatchers;
    @Autowired
    private MyCustomUserService myCustomUserService;

    private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();


    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public void setRequiresAuthenticationRequestMatcher(RequestMatcher requiresAuthenticationRequestMatcher) {
        String s=null;
        this.requiresAuthenticationRequestMatcher = requiresAuthenticationRequestMatcher;
    }

    public JwtAuthenticationFilter() {
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    public String getToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (!this.requiresAuthenticationRequestMatcher.matches(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        } else {
            String authToken = this.getToken(httpServletRequest);
            String username = JwtUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myCustomUserService.loadUserByUsername(username);
                if (JwtUtil.verifyToken(authToken)) {
                    if (userDetails != null) {
                        JwtAuthenticationToken authentication = new JwtAuthenticationToken();
                        authentication.setJwtToken(authToken);
                        authentication.setDetails(userDetails);
                        authentication.setAuthenticated(true);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {

                    }
                }
//            if(authResult != null) {
//                successfulAuthentication(httpServletRequest, httpServletResponse, filterChain, authResult);
//            } else if(!permissiveRequest(httpServletRequest)){
//                unsuccessfulAuthentication(httpServletRequest, httpServletResponse, failed);
//                return;
//            }

            }
        }
    }

    public void unsuccessfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception fail) {
        SecurityContextHolder.clearContext();
    }

    public void successfulAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain, Authentication result) {
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    public boolean permissiveRequest(HttpServletRequest httpServletRequest) {
        if (permissiveRequestMatchers == null)
            return false;
        for (RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
            if (permissiveMatcher.matches(httpServletRequest))
                return true;
        }
        return false;
    }

    public void setPermissiveRequestMatchers(String... url) {
        if (this.permissiveRequestMatchers == null) {
            this.permissiveRequestMatchers = new ArrayList<RequestMatcher>();
        }
        for (String u : url) {
            this.permissiveRequestMatchers.add(new AntPathRequestMatcher(u));
        }
    }
}
