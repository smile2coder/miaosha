package com.smile2coder.interceptor;

import com.smile2coder.constant.Constant;
import com.smile2coder.exception.UnKnowAccountException;
import com.smile2coder.exception.UnLoginException;
import com.smile2coder.model.MUser;
import com.smile2coder.service.TokenService;
import com.smile2coder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(Constant.TOKEN);
        if(token == null) {
            throw new UnLoginException();
        }
        Integer userId = tokenService.getUserId(token);
        MUser user = this.userService.selectByUserId(userId);
        if(user == null) {
            throw new UnKnowAccountException();
        }
        TokenHolder.set(token);
        UserHolder.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenHolder.remove();
        UserHolder.remove();
    }
}
