package com.fenqing168.school.api.common.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {

    private Integer width;

    private Integer height;

    private Integer codeNum;

    private String charSet = "1234567890";

    private Integer dotNum = 50;

    private Integer lineNum = 10;

    private BufferedImage bi;

    private Graphics g;

    private Random random = new Random();

    private String code;

    public String getCode() {
        return code;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public ImageUtil(Integer width, Integer height, Integer codeNum, String charSet) {
        this.width = width;
        this.height = height;
        this.codeNum = codeNum;
        this.charSet = charSet;
    }

    public ImageUtil(Integer width, Integer height, Integer codeNum) {
        this.width = width;
        this.height = height;
        this.codeNum = codeNum;
    }

    public ImageUtil(Integer width, Integer height, Integer codeNum, String charSet, Integer dotNum, Integer lineNum) {
        this.width = width;
        this.height = height;
        if(charSet != null){
            this.charSet = charSet;
        }
        this.codeNum = codeNum;
        this.codeNum = codeNum;
        this.dotNum = dotNum;
        this.lineNum = lineNum;
    }

    public void init() {
        createImage();
        bg();
        line();
        dot();
        code();
    }

    private void createImage() {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        g = bi.createGraphics();
    }

    private void bg() {
        Color color = new Color(249, 248, 248);
        g.setColor(color);
        g.fillRect(0, 0, width, height);
    }

    private void line() {
        if (lineNum > 0) {
            for (int i = 0; i < lineNum; i++) {
                int x = random.nextInt(width), y = random.nextInt(height);
                int x1 = random.nextInt(width), y1 = random.nextInt(height);
                Color color = Color.DARK_GRAY;
                g.setColor(color);
                g.drawLine(x, y, x1, y1);
            }
        }
    }

    private void dot() {
        if (dotNum > 0) {
            for (int i = 0; i < dotNum; i++) {
                int x = random.nextInt(width), y = random.nextInt(height);
                Color color = Color.DARK_GRAY;
                g.setColor(color);
                g.drawOval(x, y, 1, 1);
            }
        }
    }

    private void code() {
        int fsize = (int) (height * 0.8);//字体大小为图片高度的80%
        int fx = 0;
        int fy = fsize;
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fsize));
        StringBuilder sb = new StringBuilder();
        //写字符
        for (int i = 0; i < codeNum; i++) {
            fy = (int) ((Math.random() * 0.3 + 0.6) * height);//每个字符高低是否随机
            int index = random.nextInt(charSet.length());
            char c = charSet.charAt(index);
            g.setColor(Color.GREEN);
            g.drawString(c + "", fx, fy);
            fx += (width / codeNum) * (Math.random() * 0.3 + 0.8); //依据宽度浮动
            sb.append(c);
        }
        this.code = sb.toString();
    }

}
