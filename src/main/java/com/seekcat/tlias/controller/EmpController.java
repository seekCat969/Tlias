package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Resource
    private EmpServiceImpl esl;

    /**分页查询员工*/
    @GetMapping()
    public Result selectEmpIndividualPage(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          String name,
                                          Short gender,
                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        System.out.println("page = " + page + ", pageSize = " + pageSize);
        PageBean pageBean = esl.selectEmpIndividualPage(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**批量删除员工*/
    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable Integer[] ids) {
        esl.deleteEmps(ids);
        return Result.success();
    }

    /**插入员工*/
    @PostMapping()
    public Result InsertEmp(@RequestBody Emp emp) {
        esl.InsertEmp(emp);
        return Result.success();
    }

    /**根据ID查询员工*/
    @GetMapping("/{id}")
    public Result selectEmpWithId(@PathVariable Integer id){
        return Result.success(esl.selectEmpWithId(id));
    }

    /**修改员工*/
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp){
        esl.updateEmp(emp);
        return Result.success();
    }
}
