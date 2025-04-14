package com.seekcat.tlias.service;

import com.seekcat.tlias.pojo.PageBean;

public interface EmpService {
    PageBean selectEmpIndividualPage(Integer begin,Integer pageSize);
}
