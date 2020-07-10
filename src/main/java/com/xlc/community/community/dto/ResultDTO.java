package com.xlc.community.community.dto;

import com.xlc.community.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 *@创建人 xlc
 *@创建时间 2020-7-10
 *@描述 返回值信息类
 **/
@Data
public class ResultDTO
{
    private Integer code;
    private String  message;

    public static  ResultDTO errorOf(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static  ResultDTO errorOf(CustomizeErrorCode customizeErrorCode){

        return errorOf(customizeErrorCode.code(),customizeErrorCode.getMessage());
    }
}
