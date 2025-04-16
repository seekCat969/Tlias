package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Dept;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.DeptServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptController {
    @Resource
    private DeptServiceImpl dsi;

    /**查询部门数据*/
    @GetMapping("/depts")
    public Result selectDept() {
        return Result.success(dsi.selectDept());
    }

    /**根据ID查询部门数据*/
    @GetMapping("/depts/{id}")
    public Result selectDeptWithId(@PathVariable Integer id){
        Dept dept = dsi.selectDeptWithId(id);
        return Result.success(dept);
    }
    /**删除部门数据*/
    @DeleteMapping("/depts/{id}")
    public Result deleteDept(@PathVariable Integer id){
        dsi.deleteDept(id);
        return Result.success();
    }

    /**添加部门数据*/
    @PostMapping("/depts")
    public Result insertDept(@RequestBody Dept dept){
        dsi.insertDept(dept);
        return Result.success();
    }

    /**修改部门数据*/
    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept){
        dsi.updateDept(dept);
        return Result.success();
    }

}
