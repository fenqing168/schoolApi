package com.fenqing168.school.api.controller;

import com.fenqing168.school.api.common.utils.ImageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("util")
public class UtilController {

    @GetMapping("loginCode")
    public void imageCode(HttpServletResponse resp, HttpSession session) throws IOException {

        ImageUtil iu = new ImageUtil(300, 200, 4, null, 50, 20);
        iu.init();
        BufferedImage bi = iu.getBi();
        String code = iu.getCode();
        session.setAttribute("loginCode", code);
        ImageIO.write(bi, "png", resp.getOutputStream());

    }

}
