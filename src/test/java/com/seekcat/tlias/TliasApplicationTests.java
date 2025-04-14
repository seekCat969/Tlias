package com.seekcat.tlias;

import com.seekcat.tlias.controller.DeptController;
import com.seekcat.tlias.controller.EmpController;
import com.seekcat.tlias.mapper.DeptMapper;
import com.seekcat.tlias.pojo.Dept;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.DeptServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
class TliasApplicationTests {

    @Resource
    private DeptController dc;
    @Resource
    private DeptServiceImpl dsi;
    @Resource
    private DeptMapper dm;

    @Resource
    private EmpController ec;

    @Test
    public void testSelectDeptController(){
        dc.selectDept();
    }

    @Test
    public void testUpdateDeptController(){
        Dept dept = new Dept();
        dept.setId(1);
        dept.setName("test占位");
        dept.setUpdateTime(String.valueOf(LocalDateTime.now()));
        dc.updateDept(dept);

    }

    @Test void testSelectDeptWithIdController(){
        Result result = dc.selectDeptWithId(1);
        System.out.println(result);
    }

    @Test void testSelectEmpIndividualPage(){
        log.info("未执行");
        ec.selectEmpIndividualPage(1,5);
        log.info("已执行");
    }

}
