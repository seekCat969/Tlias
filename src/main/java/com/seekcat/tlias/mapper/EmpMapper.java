package com.seekcat.tlias.mapper;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**返回总页数*/
    @Select("select count(*) from emp")
    Long selectPage();

    /**返回每一页的数据*/
    @Select("select * from emp limit #{begin},#{pageSize}")
    List<Emp> selectEmps(Integer begin,Integer pageSize);}
