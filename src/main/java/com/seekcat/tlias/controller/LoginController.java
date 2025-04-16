package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import com.seekcat.tlias.utils.JwtUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private EmpServiceImpl esi;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        Emp emp1 = esi.login(emp);

        if(emp1 != null){
            Map<String,Object> map = new HashMap<>();
            map.put("id",emp1.getId());
            map.put("username",emp1.getUsername());
            return Result.success(JwtUtils.generateJWT(map));
        }
        return Result.error("用户名或密码错误");

    }
}
