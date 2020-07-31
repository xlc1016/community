package com.xlc.community.community.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *@创建人 xlc
 *@创建时间 2020-7-30
 *@描述 问题标签的封装类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagTDO {

    private String categoryName;
    private List<String> tags;
}
