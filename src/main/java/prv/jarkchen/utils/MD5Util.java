package prv.jarkchen.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt="1a2b3c4d";

    // 一次加密
    public static String inputPassToFormPass(String inputPass){
        String str= ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5);     // 1+2+inputPass+c
        return md5(str);
    }
    // 二次加密
    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5);     // 1+2+formPass+c
        return md5(str);
    }

    // 综合
    public static String inputPassToDBPass(String inputPass,String salt){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass,salt);
        return dbPass;
    }

    public static void main(String[] args){
        String testPassword = "123456";
        System.out.println(inputPassToFormPass(testPassword));  // 72100a46bad3df5e9a23ab6119c137f1
        System.out.println(formPassToDBPass(inputPassToFormPass(testPassword),salt));   // 4267594346f8f1ef0d518d33126948d9

        System.out.println(inputPassToDBPass(testPassword,salt));
    }
}
