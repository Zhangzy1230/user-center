package com.zzy.usercenter.util;

public class CheckUtil {
    /**
     * 校验用户名
     *      * 	用户名
     *      * 1、	大于1个字符，小于20个字符
     *      * 2、	只能出现数字和字母
     *      * 3、	不能与其他用户名重复
     *      * 密码
     *      * 1、	大于1个字符，小于20个字符
     *      * 校验通过则向数据库中插入数据（密码要加密）；否则返回错误信息
     * @param userAccount 用户名
     * @return 是否合法
     */
    public static boolean checkAccount(String userAccount){
        if(userAccount == null || userAccount.isEmpty() || userAccount.length() >= 20){
            return false;
        }
        String regex = "^[a-zA-Z0-9]+$";
        if(!userAccount.matches(regex)){
            return false;
        }
        return true;
    }
    public static boolean checkPassword(String password){
        if(password == null || password.isEmpty() || password.length() >= 20){
            return false;
        }
        return true;
    }
}
