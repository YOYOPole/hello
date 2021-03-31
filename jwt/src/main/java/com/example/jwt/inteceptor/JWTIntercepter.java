package com.example.jwt.inteceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


public class JWTIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //获取请求头中的令牌
        String token = request.getHeader("token");

        //
        try {
            DecodedJWT verify = JWTUtils.verify(token); //
            return true;//放行请求
        } catch (SignatureVerificationException e) {
            map.put("msg", "签名无效");
            e.printStackTrace();
        } catch (TokenExpiredException e) {
            map.put("msg", "Token过期");
            e.printStackTrace();
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "两次算法不一致");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "Token无效");
        }
        map.put("state", false);//设置状态
        //将map转为json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
        return false;
    }
}
