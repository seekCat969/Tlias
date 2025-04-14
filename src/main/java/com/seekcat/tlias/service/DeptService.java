package com.seekcat.tlias.service;

import com.seekcat.tlias.pojo.Dept;
import java.util.List;

public interface DeptService {

    List<Dept> selectDept();

    void deleteDept(Integer id);

    void insertDept(Dept dept);

    void updateDept(Dept dept);

    Dept selectDeptWithId(Integer id);
}
