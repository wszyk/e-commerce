package com.zyk.projectadminapi.admin.interceptor;

import com.alibaba.fastjson.JSON;
import com.zyk.projectadminapi.admin.dto.LoginInfo;
import com.zyk.projectadminapi.admin.exception.BackendClientException;
import com.zyk.projectadminapi.admin.exception.BackendUnauthenticationException;
import com.zyk.projectadminapi.admin.utils.AES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Value("${token.aes.secret}")
    private String aesSecret;

    private String[] urls = {
            "/Admin/user/login",
            "/error",
            "/Admin/user/resetPassword",
            "/Admin/user/verifyCode",
            "/Admin/user/getVerify",
            "/Admin/user/checkVerify"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否为预检请求
        String method = request.getMethod();
        if (method.equals("OPTIONS")){
            return true;
        }
        boolean contains = Arrays.asList(urls).contains(request.getRequestURI());
        if (contains){
            return true;
        }
        String authorizationStr = request.getHeader("Authorization");
        if (authorizationStr == null|| authorizationStr.equals("undefined")){
           throw new BackendUnauthenticationException("Unauthentication");
        }

        String[] s = authorizationStr.split(" ");
        String token = s[1];
        LoginInfo loginInfo;
        try{
            //todo decrypted token with aes or rsa
//            byte[] decode = Base64.getDecoder().decode(token);
            String loginJsonStr = AES.decrypt(token, aesSecret);
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
        if (currentTimestamp > expireTimestamp){
            throw new BackendUnauthenticationException("Unauthentication: token is expired");
        }

        request.setAttribute("currentUserId",userId);
        request.setAttribute("username",username);

        return true;
    }
}
