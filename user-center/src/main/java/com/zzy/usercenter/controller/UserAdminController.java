package com.zzy.usercenter.controller;

import com.zzy.usercenter.mapper.UserMapper;
import com.zzy.usercenter.result.Code;
import com.zzy.usercenter.result.Result;
import com.zzy.usercenter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/admin")
public class UserAdminController {
    private static final String USER_LOGIN_STATUS = "userLoginStatus";
    @Resource
    UserService userService;
    @GetMapping("selectUserByAccount/{userAccount}")
    public Result selectUserByAccount(@PathVariable String userAccount, HttpServletRequest httpServletRequest){
        Long id = (Long)httpServletRequest.getSession().getAttribute(USER_LOGIN_STATUS);
        if(id == null || id != 1){
            return Result.error(Code.CODE_NOT_ADMIN);
        }
        return userService.selectUserByAccount(userAccount);
    }

    @DeleteMapping("deleteUserByAccount/{userAccount}")
    public Result deleteUserByAccount(@PathVariable String userAccount, HttpServletRequest httpServletRequest){
        Long id = (Long)httpServletRequest.getSession().getAttribute(USER_LOGIN_STATUS);
        if(id == null || id != 1){
            return Result.error(Code.CODE_NOT_ADMIN);
        }
        return userService.deleteUserByAccount(userAccount);
    }



}
