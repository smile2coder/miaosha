package com.smile2coder.init;

import com.smile2coder.service.TokenService;
import com.smile2coder.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author zxt
 * @date 1/2/21
 * @desc 模拟生成登录用户，用于测试
 */
//@Component
@Slf4j
public class UserInit implements ApplicationRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 1000; i++) {
//            MUser user = new MUser();
//            user.setUsername("tom" + i);
//            user.setNickname("tom" + i);
//            user.setCreateTime(new Date());
//            user.setUpdateTime(new Date());
//            user.setPassword("tom" + i);
//            user.setStatus(BaseModel.STATUS_NORMAL);
//            userService.insert(user);

            this.tokenService.saveToken(i + "", i+1);
            log.info("生成用户--> {}", "tom" + i);
        }
    }
}
