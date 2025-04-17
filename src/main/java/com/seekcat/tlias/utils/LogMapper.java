package com.seekcat.tlias.utils;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface LogMapper {

    @Insert("insert into log (update_time, description) VALUES (#{now},#{str})")
    void toLog(LocalDateTime now, String str);

}
