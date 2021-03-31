package com.example.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils{

    private static final String  SIGN =  "Token!#3$1!97^";

    /**
     * 生成token
     * @param map 传入令牌信息
     * @return
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60);
        map.forEach((k,v)->{
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    /**
     * 验证Token
     * @param token 前端携带的token信息
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
