package com.zzy.usercenter.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@AllArgsConstructor
@Data
public class Result {
    Code code;
    HashMap data;

    public static Result error(Code code){
        return new Result(code,null);
    }

    public static Result ok(HashMap data){
        return new Result(Code.CODE_OK,data);
    }

}
