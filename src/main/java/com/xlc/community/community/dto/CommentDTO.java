package com.xlc.community.community.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@创建人 xlc
 *@创建时间 2020-7-9
 *@描述 回复的组装类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer parentId; // 父类id
    private String content; // 回复内容
    private Integer type;
}
