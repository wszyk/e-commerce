package com.zyk.projectadminapi.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.zyk.projectadminapi.admin.dto.LoginInfo;
import com.zyk.projectadminapi.admin.exception.BackendClientException;
import com.zyk.projectadminapi.admin.exception.BackendUnauthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private String[] urls = {"/Admin/user/login","/error"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean contains = Arrays.asList(urls).contains(request.getRequestURI());
        if (contains){
            return true;
        }
        String authorizationStr = request.getHeader("Authorization");
        if (authorizationStr == null){
            throw new BackendUnauthenticationException("Unauthentication");
        }
        String[] s = authorizationStr.split(" ");
        String token = s[1];
        LoginInfo loginInfo;
        try{
            //todo decrypted token with aes or rsa
            byte[] decode = Base64.getDecoder().decode(token);
            String loginJsonStr = new String(decode, "UTF-8");
            loginInfo = JSON.parseObject(loginJsonStr, LoginInfo.class);
        }catch (Exception ex){
            throw new BackendClientException("auth invalid caused by some issues");
        }
        Long userId = loginInfo.getUserId();
        String username = loginInfo.getUsername();
        if (username == null){
            throw new BackendUnauthenticationException("Unauthentication");
        }
        long expireTimestamp = loginInfo.getExpirationTime().getTime();
        Date currentTime = new Date();
        long currentTimestamp = currentTime.getTime();
        if (username == null || username.isEmpty()){
            throw new BackendUnauthenticationException("Unauthentication: username is null or empty");
        }
        System.out.println("");
        if (currentTimestamp < expireTimestamp){
            throw new BackendUnauthenticationException("Unauthentication: token is expired");
        }

        request.setAttribute("userId",userId);
        request.setAttribute("username",username);

        return true;
    }
}
