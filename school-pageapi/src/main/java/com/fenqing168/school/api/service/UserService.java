package com.fenqing168.school.api.service;

import com.fenqing168.school.api.common.vo.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户注册
     * @param code 验证码
     * @param email 邮箱
     * @param password 密码
     * @param session 会话
     * @return
     */
    R register(String code, String email, String password, HttpSession session, HttpServletRequest req);

    /**
     * 登陆
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    R login(String email, String password);
}
