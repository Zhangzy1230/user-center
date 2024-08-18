package com.zzy.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  
  
public class PasswordHashing {  
  
    // 使用SHA-256算法对密码进行哈希  
    public static String hashPassword(String password) {  
        try {  
            // 创建一个MessageDigest实例，初始化为SHA-256算法  
            MessageDigest digest = MessageDigest.getInstance("SHA-256");  
            // 使用指定的字节更新摘要  
            byte[] encodedhash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));  
  
            // 将byte数组转换为十六进制字符串  
            StringBuilder hexString = new StringBuilder();  
            for (int i = 0; i < encodedhash.length; i++) {  
                String hex = Integer.toHexString(0xff & encodedhash[i]);  
                if(hex.length() == 1) hexString.append('0');  
                hexString.append(hex);  
            }  
  
            return hexString.toString();  
  
        } catch (NoSuchAlgorithmException e) {  
            throw new RuntimeException(e);  
        }  
    }  
  
//    public static void main(String[] args) {
//        String password = "mySecurePassword";
//        String hashedPassword = hashPassword(password);
//        System.out.println("原始密码: " + password);
//        System.out.println("哈希后的密码: " + hashedPassword);
//    }
}