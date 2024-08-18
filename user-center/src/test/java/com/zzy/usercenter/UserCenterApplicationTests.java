package com.zzy.usercenter;

import com.zzy.usercenter.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterApplicationTests {
    @Resource
    private UserService userService;
    @Test
    void contextLoads() {

    }

    @Test
    void testRegister() {
        userService.register("admin","admin");
//        userService.register("admin￥%","admin");
    }

}
