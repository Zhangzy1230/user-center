package com.zzy.usercenter.service.impl;
import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzy.usercenter.result.Code;
import com.zzy.usercenter.result.Result;
import com.zzy.usercenter.model.domain.User;
import com.zzy.usercenter.service.UserService;
import com.zzy.usercenter.mapper.UserMapper;
import com.zzy.usercenter.util.PasswordHashing;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
* @author zzy
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-08-16 16:06:35
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;
    private static final String USER_LOGIN_STATUS = "userLoginStatus";


    /**
     * 注册功能实现
     *
     * @param userAccount 用户账号
     * @param password  密码
     * @return  结果
     */
    @Override
    public Result register(String userAccount, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> queryWrapper = userQueryWrapper.lambda().eq(User::getUserAccount, userAccount);
        if(userMapper.selectOne(queryWrapper) != null){
            return Result.error(Code.CODE_ACCOUNT_REGISTED);
        }
        User user = new User();
//        user.setId(0L);
//        user.setUsername("");
        user.setUserAccount(userAccount);
//        user.setAvatarUrl("");
//        user.setGender(0);
        user.setPassword(PasswordHashing.hashPassword(password));
//        user.setPhone("");
//        user.setEmail("");
//        user.setStatus(0);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setIsDelete(0);
//        user.setUserRole(0);
        int i = userMapper.insert(user);
        return i>0?Result.ok(null):Result.error(Code.CODE_ERROR);
    }
    /**
     * 登录功能实现
     * @param userAccount 用户名
     * @param password 密码
     * @return 返回脱敏后的用户信息或者错误信息
     */
    @Override
    public Result login(String userAccount, String password, HttpServletRequest httpServletRequest){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> queryWrapper = userQueryWrapper.lambda().eq(User::getUserAccount, userAccount);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.error(Code.CODE_ACCOUNT_NOT_EXIST);
        }
        if(!PasswordHashing.hashPassword(password).equals(user.getPassword())){
            return Result.error(Code.CODE_PASSWORD_NOT_MATCH);
        }
//        user.setId(0L);
//        user.setUsername("");
//        user.setUserAccount("");
//        user.setAvatarUrl("");
//        user.setGender(0);
        user.setPassword(null);
//        user.setPhone("");
//        user.setEmail("");
//        user.setStatus(0);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
//        user.setIsDelete(0);
//        user.setUserRole(0);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(USER_LOGIN_STATUS,user.getId());

        HashMap<Object, Object> data = new HashMap<>();
        data.put(user.getId(),user);
        return Result.ok(data);
    }
    /**
     *  我是谁？
     */
    @Override
    public Result who(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Object attribute = session.getAttribute(USER_LOGIN_STATUS);
        if(attribute == null){
            return Result.error(Code.CODE_ERROR);
        }
        Long id = (Long)attribute;
        User user = userMapper.selectById(id);
        user.setPassword(null);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(id,user);
        return Result.ok(hashMap);
    }
    /**
     * 查询用户
     * @return  所有用户信息或者错误信息
     */
    @Override
    public Result selectUserByAccount(String userAccount) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getUserAccount,userAccount);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        HashMap<Object, Object> data = new HashMap<>();
        for (User u : users){
            data.put(u.getId(),u);
        }
        return Result.ok(data);
    }
    /**
     * 根据用户名删除用户
     * @param userAccount
     * @return
     */
    @Override
    public Result deleteUserByAccount(String userAccount) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserAccount,userAccount);
        int i = userMapper.delete(userLambdaQueryWrapper);
        if(i < 1){
            return Result.error(Code.CODE_ERROR);
        }
        return Result.ok(null);
    }

}




