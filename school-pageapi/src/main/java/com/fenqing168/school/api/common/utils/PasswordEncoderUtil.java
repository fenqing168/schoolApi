package com.fenqing168.school.api.common.utils;

import java.security.MessageDigest;
import java.util.Random;

public class PasswordEncoderUtil {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private final static int SELT_SIZE = 20;

    public String getSelt(){
        return salt.toString();
    }

    private Object salt;
    private String algorithm;

    public PasswordEncoderUtil(String algorithm) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= SELT_SIZE; i++){
            int r = random.nextInt(hexDigits.length);
            sb.append(hexDigits[r]);
        }

        this.salt = sb.toString();
        this.algorithm = algorithm;
    }

    public PasswordEncoderUtil(Object salt, String algorithm) {

        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * 加密
     * @param rawPass 原始密码
     * @return
     */
    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }

    /**
     * 验证密码
     * @param encPass
     * @param rawPass
     * @return
     */
    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    /**
     * 获取密码和盐
     * @param password
     * @return
     */
    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 字符转转字节数组
     * @param hex
     * @return
     */
    public byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * 16进制转十进制
     * @param c
     * @return
     */
    private int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * byte转16进制
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] a){
        PasswordEncoderUtil passwordEncoder = new PasswordEncoderUtil("MD5");
        System.out.println(passwordEncoder.getSelt());
        System.out.println(passwordEncoder.encode("wd19980602"));
    }

}