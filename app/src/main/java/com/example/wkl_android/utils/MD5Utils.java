package com.example.wkl_android.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 *
 * @author li
 * @since 2018/10/25
 */
public final class MD5Utils {

    private MD5Utils() {
    }

    /**
     * 获取字符串的MD5值,大写
     *
     * @param src 源
     * @return MD5值
     */
    public static String getMD5String(String src) {
        if (src == null) {
            return "";
        }
        try {
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            byte[] bytes = md5.digest(src.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
