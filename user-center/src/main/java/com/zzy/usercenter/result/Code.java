package com.zzy.usercenter.result;

public enum Code {
    CODE_NOT_ADMIN(404,"不是管理员"),
    CODE_PASSWORD_NOT_MATCH(404,"密码不对"),
    CODE_ACCOUNT_NOT_EXIST(404,"用户名不存在"),
    CODE_ACCOUNT_REGISTED(404,"用户名已被其他人注册"),
    CODE_ACCOUNT_ERROR(404,"用户名不符合要求"),
    CODE_PASSWORD_ERROR(404,"密码不符合要求"),
    CODE_ERROR(404,"未知错误"),
    CODE_OK(200,"ok");
    int code;
    String message;
    Code(int code,String message){
        this.code = code;
        this.message = message;
    }
}
