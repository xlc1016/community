package com.xlc.community.community.enums;
/**
 *@创建人 xlc
 *@创建时间 2020-7-10
 *@描述 回复的枚举类
 **/
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

     CommentTypeEnum(Integer type){
        this.type = type;

    }

}
