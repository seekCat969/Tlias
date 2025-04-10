package com.seekcat.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;

//    增删改
    public static Result success(){
        return new Result(1,"success",null);
    }

//    查询
    public static Result success(Object data){
        return new Result(1,"success",data);
    }

    public static Result error(String msg){
        return new Result(0,msg,null);
    }


}
