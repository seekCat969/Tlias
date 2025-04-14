package com.seekcat.tlias.service.impl;

import com.seekcat.tlias.mapper.EmpMapper;
import com.seekcat.tlias.pojo.PageBean;
import com.seekcat.tlias.service.EmpService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService {
    @Resource
    EmpMapper em;

    @Override
    public PageBean selectEmpIndividualPage(Integer page, Integer pageSize) {
        Integer begin = (page - 1) * pageSize;
        return new PageBean(em.selectPage(), em.selectEmps(begin, pageSize));
    }
}
