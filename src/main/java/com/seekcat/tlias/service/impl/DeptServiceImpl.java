package com.seekcat.tlias.service.impl;

import com.seekcat.tlias.mapper.DeptMapper;
import com.seekcat.tlias.pojo.Dept;
import com.seekcat.tlias.service.DeptService;
import com.seekcat.tlias.utils.SqlLog;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    private static final Logger log = LoggerFactory.getLogger(DeptServiceImpl.class);
    @Resource
    private DeptMapper dm;
    @Resource
    private EmpServiceImpl esi;
    @Resource
    private SqlLog sqlLog;

    @Override
    public List<Dept> selectDept() {
        return dm.selectDept();
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //事务管理 rollbackFor设置不止在RuntimeException时回滚
    public void deleteDept(Integer id) {
        try {
            dm.deleteDept(id);
            int i = 1 / 0;
            esi.deleteEmpWithDeptId(id);
        } finally {
            sqlLog.insertLog("记录了日志");
        }
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
