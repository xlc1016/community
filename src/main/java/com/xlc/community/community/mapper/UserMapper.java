package com.xlc.community.community.mapper;


import com.xlc.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
* @author :xlc
* @date: 2020-3-20
* @description: user mapper
 *
*/

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,token,accountId,gmtCreate,gmtModified)values(#{name},#{token},#{accountId},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByTokne(@Param("token") String token);
}
