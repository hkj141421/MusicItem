package com.example.demo.Controller;

import com.example.demo.Entity.AjaxResponseBody;
import com.example.demo.Entity.Fans;
import com.example.demo.Entity.SecurityUser;
import com.example.demo.Entity.User;
import com.example.demo.Service.FansService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by forget on 2019/3/12.
 */
@RestController
public class UserController {
    @Autowired
    FansService FService;
    @Autowired
    UserService UService;

    @PutMapping("/api/newuser")
    public AjaxResponseBody register(HttpServletRequest request, HttpServletResponse response) {
        //新增用户，用户注册
        int num = 0;
        String VerificationCode = (String) request.getSession().getAttribute("VerificationCode");
        request.getSession().removeAttribute("VerificationCode");

        if (VerificationCode.equals(request.getParameter("emailverifycode"))) {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setUseraccount(request.getParameter("useraccount"));
            user.setUserpassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            num = UService.InsertUser(user);
            if (num != 0) {
                return new AjaxResponseBody("200", "success", null);
            } else {
                return new AjaxResponseBody("233", "注册失败", null);
            }
        } else {
            return new AjaxResponseBody("233", "邮箱验证失败", null);
        }
    }

    @PutMapping("/api/user/follow/{id:[1-9][0-9]*}")
    public AjaxResponseBody followed(@PathVariable Long id) {
        //添加粉丝
        AjaxResponseBody resp = new AjaxResponseBody();
        SecurityUser u = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = u.getUser();
        Fans fans = new Fans(user.getUserid(), id);
        int num = FService.addFans(fans);
        if (num == 1) {
            resp.setStatus("200");
            resp.setMsg("follow success");
        } else {
            resp.setStatus("403");
            resp.setMsg("follow fail");
        }
        return resp;
    }

    @PostMapping("/api/user/info")
    public AjaxResponseBody updateinfo(@RequestBody User user) {
        //修改用户信息
        Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
        user.setUserid(userid);
        int num = UService.UpdateUser(user);
        if (num == 1) {
            UService.getUserById(user.getUserid());
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "修改失败", null);
        }
    }

    @GetMapping("/api/user/info")
    public AjaxResponseBody getUserInfo(@RequestParam("userid") Long userid, @RequestParam(name = "likeuserid", required = false) Long likeuserid) {
        //获取用户信息
        Object userinfo = null;
        if (likeuserid == null) {
            userinfo = UService.getUserById(userid);
        } else {
            userinfo = UService.getUserIsFans(userid, likeuserid);
        }
        if (userinfo != null) {
            return new AjaxResponseBody("200", "success", userinfo);
        } else {
            return new AjaxResponseBody("233", "获取失败", null);
        }
    }

    @PostMapping("/api/user/info/pwd")
    public AjaxResponseBody updateUserpwd(@RequestParam("oldpwd") String oldpwd, @RequestParam("newpwd") String newpwd) {
        //修改密码
        Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
        User user = new User();
        user.setUserid(userid);
        String pwd = UService.getUserById(userid).getUserpassword();
        if (pwd.equals(oldpwd)) {
            user.setUserpassword(newpwd);
            int i = UService.UpdateUser(user);
            if (i != 0) {
                return new AjaxResponseBody("200", "success", null);
            } else {
                return new AjaxResponseBody("233", "修改失败", null);
            }
        } else {
            return new AjaxResponseBody("233", "密码验证错误", null);
        }
    }

    @PostMapping("/api/user/info/email")
    public AjaxResponseBody updateUserEmail(HttpServletRequest request, @RequestParam("code") String Code) {
        //修改邮箱
        Long userid = ((SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getUserid();
        String VerCode = (String) request.getSession().getAttribute("emailcode");
        if (VerCode.equals(Code)) {
            User user = new User();
            user.setUserid(userid);
            user.setEmail((String) request.getSession().getAttribute("email"));
            int i = UService.UpdateUser(user);
            if (i != 0) {
                return new AjaxResponseBody("200", "success", null);
            } else {
                return new AjaxResponseBody("233", "修改失败", null);
            }
        } else {
            return new AjaxResponseBody("233", "密码验证错误", null);
        }
    }

    @PostMapping("/api/emailCode")
    public AjaxResponseBody checkEmailCode(HttpServletRequest request, @RequestParam("code") String Code) {
        //验证邮箱验证码
        // SecurityUser securityUser=(SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String VerCode = (String) request.getSession().getAttribute("emailcode");
        if (VerCode.equals(Code)) {
            return new AjaxResponseBody("200", "success", null);
        } else {
            return new AjaxResponseBody("233", "邮箱验证错误", null);
        }
    }

    @GetMapping("/api/search/user")
    public AjaxResponseBody searchUser(@RequestParam(name = "key", required = false) String key, @RequestParam(name = "pagenum") Integer pagenum, @RequestParam(name = "size") Integer size) {
        List<User> result = UService.searchPage(key, pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/search/user/pagecount")
    public AjaxResponseBody CountSearchUser(@RequestParam(name = "key", required = false) String key) {
        Long result = UService.CountSearchPage(key);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/recommend/user")
    public AjaxResponseBody getRecommendUser(@RequestParam(name = "userid", required = false) Long userid) {
        List<User> recommendUser = UService.getRecommedUser(userid, 5L);
        if (recommendUser != null) {
            return new AjaxResponseBody("200", "success", recommendUser);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/user")
    public AjaxResponseBody getAllUser(@RequestParam(name = "pagenum") Long pagenum, @RequestParam("size") Long size) {
        List<User> result = UService.getUserPage(pagenum, size);
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @GetMapping("/api/backstage/user/count")
    public AjaxResponseBody getAllUserCount() {
        Long result = UService.getUserCount();
        if (result != null) {
            return new AjaxResponseBody("200", "success", result);
        }
        return new AjaxResponseBody("233", "获取失败", null);
    }

    @PostMapping("/api/backstage/user")
    public AjaxResponseBody UpdateData(@RequestParam("status") String status, @RequestParam("userid") Long userid) {
        User user = new User();
        user.setUserid(userid);
        user.setStatus(status);
        int num = UService.updateAllColum(user);
        if (num != 0) {
            return new AjaxResponseBody("200", "success", num);
        }
        return new AjaxResponseBody("233", "未修改任何数据", num);
    }
}
