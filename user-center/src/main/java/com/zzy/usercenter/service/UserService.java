package com.zzy.usercenter.service;

import com.zzy.usercenter.result.Result;
import com.zzy.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author zzy
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-08-16 16:06:35
*/
public interface UserService extends IService<User> {
    Result register(String userAccount, String password);
    Result login(String userAccount, String password, HttpServletRequest httpServletRequest);
    Result who(HttpServletRequest httpServletRequest);
    Result selectUserByAccount(String userAccount);
    Result deleteUserByAccount(String userAccount);
}
