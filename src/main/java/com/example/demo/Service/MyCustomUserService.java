package com.example.demo.Service;

import com.example.demo.Entity.SecurityUser;
import com.example.demo.Entity.User;
import com.example.demo.ServiceImp.UserServiceImpl;
import com.example.demo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by forget on 2019/3/6.
 */
@Component
public class MyCustomUserService implements UserDetailsService {

    @Autowired
    public UserServiceImpl UService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SecurityUser securityUser = null;
        User user = UService.getUserByAccount(s);
        if (user != null) {
            List<String> AuthList = UService.getUserAuthority(s);
            for (String str : AuthList) {
                authorities.add(new SimpleGrantedAuthority(str));
            }
            securityUser = new SecurityUser(authorities);
            securityUser.setUsername(s);
            securityUser.setUser(user);
            securityUser.setPassword(user.getUserpassword());
        }
        return securityUser;
    }

    public String CreateUserInfoToken(UserDetails user) throws Exception {
        Map map = new HashMap<String, String>();
        map.put("username", user.getUsername());
        String token = JwtUtil.createJwtToken(map);
        return token;
    }
}
