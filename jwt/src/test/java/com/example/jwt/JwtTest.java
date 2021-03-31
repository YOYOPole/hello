package com.example.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;


@SpringBootTest
public class JwtTest {

   @Test
    public void crteateToken() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 120);

        String token = JWT.create()
                .withClaim("username", "admin") //令牌
                .withClaim("age", 18) //不要写入敏感信息，例如密码
                .withExpiresAt(instance.getTime())  //过期时间
                .sign(Algorithm.HMAC256("#3$1!97^"));  //加密算法
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTY5MTIyNDIsImFnZSI6MTgsInVzZXJuYW1lIjoiYWRtaW4ifQ.QUi0o00Blla1uyBeWKxZQ1_iyQ96kENe747Va-IbJbc";
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("#3$1!97^")).build();
        DecodedJWT decoded = verifier.verify(token);

        Claim username = decoded.getClaim("username");
        Claim age = decoded.getClaim("age");
        System.out.println(username.asString());
        System.out.println(age.asInt());
    }
}
