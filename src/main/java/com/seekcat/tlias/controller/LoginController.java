package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private EmpServiceImpl esi;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        Emp emp1 = esi.login(emp);
        return emp1 == null ? Result.error("用户名或密码错误") : Result.success();
    }
}
