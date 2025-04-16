package com.seekcat.tlias.service.impl;

import com.seekcat.tlias.mapper.DeptMapper;
import com.seekcat.tlias.pojo.Dept;
import com.seekcat.tlias.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper dm;

    @Override
    public List<Dept> selectDept() {
        return dm.selectDept();
    }

    @Override
    public void deleteDept(Integer id) {
        dm.deleteDept(id);
    }

    @Override
    public void insertDept(Dept dept) {
        dept.setCreateTime(String.valueOf(LocalDateTime.now()));
        dept.setUpdateTime(String.valueOf(LocalDateTime.now()));
        dm.insertDept(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(String.valueOf(LocalDateTime.now()));
        dm.updateDept(dept);
    }

    @Override
    public Dept selectDeptWithId(Integer id) {
        return dm.selectDeptWithId(id);
    }
}
