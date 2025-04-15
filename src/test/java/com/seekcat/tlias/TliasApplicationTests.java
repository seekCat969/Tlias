package com.seekcat.tlias;

import com.seekcat.tlias.controller.DeptController;
import com.seekcat.tlias.controller.EmpController;
import com.seekcat.tlias.mapper.DeptMapper;
import com.seekcat.tlias.mapper.EmpMapper;
import com.seekcat.tlias.pojo.Dept;
import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.DeptServiceImpl;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
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
    @Resource
    private EmpServiceImpl esi;
    @Resource
    private EmpMapper em;

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

//    @Test void testSelectEmpIndividualPage(){
//        log.info("未执行");
//        ec.selectEmpIndividualPage(1,5);
//        log.info("已执行");
//    }
//
//    @Test void testSelectEmpIndividualPage2(){
//        ec.selectEmpIndividualPage(1,5);
//    }

    @Test void testSelectEmpIndividualPage(){
        ec.selectEmpIndividualPage(1,5,"张", (short) 1, LocalDate.now(),LocalDate.now());
    }
    @Test
    void testDeleteEmps(){
        Integer[] nums = {1,2,3};
        em.deleteEmps(nums);
    }

    @Test
    void testInsertEmp(){
        Emp emp = new Emp();
        emp.setName("李泽康");
        emp.setUsername("lizekang");
        emp.setGender((short) 2);
        emp.setImage("5.jpg");
        emp.setJob((short) 1);
        emp.setEntrydate(LocalDate.now());
        emp.setDeptId(1);
//        emp.setCreateTime(LocalDateTime.now());
//        emp.setUpdateTime(LocalDateTime.now());
//        em.InsertEmp(emp);
        ec.InsertEmp(emp);
    }

    @Test
    void testUpload() throws IOException {
        File file = new File("C:\\Users\\57154\\Pictures\\Screenshots\\Screenshot 2024-09-12 174710.png");
        FileInputStream input = new FileInputStream(file);

        MultipartFile multipartFile = new MockMultipartFile(
                "file",                             // 参数名称（比如表单名）
                file.getName(),                    // 原始文件名
                "image/png",                       // Content-Type 类型
                input                              // 文件流
        );
        ec.uploadImage(multipartFile);

    }


}
