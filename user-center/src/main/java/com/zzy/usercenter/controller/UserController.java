package com.zzy.usercenter.controller;

import com.zzy.usercenter.result.Code;
import com.zzy.usercenter.result.Result;
import com.zzy.usercenter.request.RegisterAndLoginRequest;
import com.zzy.usercenter.service.UserService;
import com.zzy.usercenter.util.CheckUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("register")
    public Result register(@RequestBody RegisterAndLoginRequest user){
        String userAccount = user.getUserAccount();
        String password = user.getPassword();
        if(!CheckUtil.checkAccount(userAccount)){
            return Result.error(Code.CODE_ACCOUNT_ERROR);
        }
        if(!CheckUtil.checkPassword(password)){
            return Result.error(Code.CODE_PASSWORD_ERROR);
        }
        return userService.register(userAccount,password);
    }

    @PostMapping("login")
    public Result login(@RequestBody RegisterAndLoginRequest user, HttpServletRequest httpServletRequest){
        String userAccount = user.getUserAccount();
        String password = user.getPassword();
        if(!CheckUtil.checkAccount(userAccount)){
            return Result.error(Code.CODE_ACCOUNT_ERROR);
        }
        if(!CheckUtil.checkPassword(password)){
            return Result.error(Code.CODE_PASSWORD_ERROR);
        }
        return userService.login(userAccount,password,httpServletRequest);
    }

    @GetMapping("who")
    public Result who(HttpServletRequest httpServletRequest){

        return userService.who(httpServletRequest);
    }

//    public boolean check(String userAccount, String password){
//
//    }
}
