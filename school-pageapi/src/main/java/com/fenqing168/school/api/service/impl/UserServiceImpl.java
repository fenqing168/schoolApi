package com.fenqing168.school.api.service.impl;

import com.fenqing168.school.api.common.utils.CryptoUtil;
import com.fenqing168.school.api.common.utils.IpUtil;
import com.fenqing168.school.api.common.utils.PasswordEncoderUtil;
import com.fenqing168.school.api.common.vo.R;
import com.fenqing168.school.api.dao.UserDao;
import com.fenqing168.school.api.pojo.UserEntity;
import com.fenqing168.school.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public R register(String code, String email, String password, HttpSession session, HttpServletRequest req) {

        String loginCode = null;
        Object codeo = session.getAttribute("loginCode");
        if(codeo != null){
            loginCode = codeo.toString();
        }

        if(!code.equalsIgnoreCase(loginCode)){
            return R.error("验证码不正确！");
        }

        if(email == null){
            return R.error("邮箱不能为空！");
        }

        if(password == null){
            return R.error("密码不能为空！");
        }

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user = userDao.queryObjectByBean(user);

        if(user != null){
            return R.error(email + "已经注册，请直接登录");
        }



        PasswordEncoderUtil passwordEncoder = new PasswordEncoderUtil("MD5");

        user = new UserEntity();
        String salt = passwordEncoder.getSelt();
        user.setSalt(salt);

        password = passwordEncoder.encode(password);
        user.setPassword(password);

        user.setEmail(email);
        user.setNickName(email);
        user.setUid(UUID.randomUUID().toString());
        user.setUsername(email);
        user.setIcon("http://cdn4.freepik.com/image/th/318-35708.jpg");
        user.setSex(0);
        user.setStatus(1);

        String ip = IpUtil.getIPAddress(req);
        user.setIp(ip);

        Date cu = new Date();
        user.setCreateTime(cu);
        user.setUpdateTime(cu);

        int num = userDao.insert(user);

        return R.ok("注册成功！");
    }

    /**
     * 登陆
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    public R login(String email, String password) {

        if(email == null){
            return R.error("邮箱不能为空！");
        }

        if(password == null){
            return R.error("密码不能为null");
        }

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user = userDao.queryObjectByBean(user);

        if(user == null){
            return R.error(email + "用户不存在！");
        }

        PasswordEncoderUtil passwordEncoder = new PasswordEncoderUtil(user.getSalt(), "MD5");
        password = passwordEncoder.encode(password);
        if(!password.equals(user.getPassword())){
            return R.error("用户名或者密码错误！");
        }

        String token = CryptoUtil.encode64(CryptoUtil.DEFAULT_SECRET_KEY1, user.getUid());

        return R.ok("登陆成功", token);
    }
}
