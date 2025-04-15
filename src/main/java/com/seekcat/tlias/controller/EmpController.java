package com.seekcat.tlias.controller;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;
import com.seekcat.tlias.pojo.Result;
import com.seekcat.tlias.service.impl.EmpServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Resource
    private EmpServiceImpl esl;

    /**
     * 分页查询员工
     */
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

    /**
     * 批量删除员工
     */
    @DeleteMapping("/{ids}")
    public Result deleteEmps(@PathVariable Integer[] ids) {
        esl.deleteEmps(ids);
        return Result.success();
    }

    @PostMapping()
    public Result InsertEmp(@RequestBody Emp emp) {
        log.info("debug start");
        esl.InsertEmp(emp);
        log.info("debug end");
        return Result.success();
    }

    @PostMapping("/upload")
    public Result uploadImage(MultipartFile image) throws IOException {
        String[] str = image.getOriginalFilename().split("\\.");
        StringBuilder sb = new StringBuilder();

        Arrays.stream(UUID.randomUUID().toString().split("-")).forEach(sb::append);

        String fileName = sb.toString() + '.' + str[str.length - 1];
        image.transferTo(new File("D:\\code\\MD文档", fileName));

        return Result.success();
    }
}
