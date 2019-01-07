package com.fenqing168.school.api.controller;

import com.fenqing168.school.api.common.vo.R;
import com.fenqing168.school.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param code 验证码
     * @param email 邮箱
     * @param password 密码
     * @param session 会话
     * @return
     */
    @PostMapping("register")
    public R register(@RequestParam("captcha_v") String code, String email, String password, HttpSession session, HttpServletRequest req){
        return userService.register(code, email, password, session, req);
    }



}
