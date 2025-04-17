package com.seekcat.tlias.mapper;

import com.seekcat.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {

    //    返回Dept中的所有数据
    @Select("select * from dept")
    List<Dept> selectDept();

    @Delete("delete from dept where id = #{id}")
    void deleteDept(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values " +
            "(#{name},#{createTime},#{updateTime})")
    void insertDept(Dept dept);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept selectDeptWithId(Integer id);

}
