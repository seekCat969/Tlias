package com.seekcat.tlias.mapper;

import com.seekcat.tlias.pojo.Emp;
import com.seekcat.tlias.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    /**返回总页数*/
//    @Select("select count(*) from emp")
//    Long selectPage();
//
//    /**返回每一页的数据*/
//    @Select("select * from emp limit #{begin},#{pageSize}")
//    List<Emp> selectEmps(Integer begin,Integer pageSize);

//    @Select("select * from emp")
    List<Emp> selectEmps(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmps(Integer[] ids);

    void InsertEmp(Emp emp);

    @Select("select * from emp where id = #{name}")
    Emp selectEmpWithId(Integer id);

    void updateEmp(Emp emp);
}


