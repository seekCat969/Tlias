package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.PageBean;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmpController {
    @Resource
    private EmpServiceImpl esl;

    @GetMapping("/emps")
    public Result selectEmpIndividualPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
//        System.out.println("page = " + page + ", pageSize = " + pageSize);
        PageBean pageBean = esl.selectEmpIndividualPage(page, pageSize);
        return Result.success(pageBean);
    }
}
