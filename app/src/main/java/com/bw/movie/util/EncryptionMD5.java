package com.bw.movie.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author: Xuexiandong
 * data: 2019/10/23 11:11:13
 * function：MD5加密
 */
public class EncryptionMD5 {

    /**
     *  MD5加密
     * @param sourceStr
     * @return
     */
    public  String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
