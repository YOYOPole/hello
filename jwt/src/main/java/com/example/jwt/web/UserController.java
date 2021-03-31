package com.example.jwt.web;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.entity.User;
import com.example.jwt.service.IUserService;
import com.example.jwt.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @RequestMapping("/login")
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User userDB = userService.login(user);//认证通过
            //认证逻辑
            HashMap<String, String> payload = new HashMap<>();
            payload.put("name", userDB.getName());
            payload.put("password", userDB.getPassword());
            //生成令牌
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);//相应token
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @PostMapping("/test")
    public Map<String, Object> test(String token) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("state", true);
        map.put("msg", "请求成功");

        //do soming

        return map;
    }
}

