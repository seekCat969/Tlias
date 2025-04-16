package com.seekcat.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.seekcat.tlias.mapper.EmpMapper;
import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;
import com.seekcat.tlias.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    EmpMapper em;

    @Override
    public PageBean selectEmpIndividualPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        Integer begin = (page - 1) * pageSize;
//        return new PageBean(em.selectPage(), em.selectEmps(begin, pageSize));
        PageHelper.startPage(page, pageSize);
        Page<Emp> p = (Page<Emp>) em.selectEmps(name,gender,begin,end);

        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteEmps(Integer[] ids) {
        em.deleteEmps(ids);
    }

    @Override
    public void InsertEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        em.InsertEmp(emp);
    }

    @Override
    public Emp selectEmpWithId(Integer id) {
        return em.selectEmpWithId(id);
    }

    @Override
    public void updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        em.updateEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return em.login(emp);
    }
}
