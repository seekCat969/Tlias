package com.seekcat.tlias.service;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    PageBean selectEmpIndividualPage(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmps(Integer[] ids);

    void InsertEmp(Emp emp);

    Emp selectEmpWithId(Integer id);

    void updateEmp(Emp emp);
}
