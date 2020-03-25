package com.xlc.community.community;

import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
@MapperScan(basePackages = "com.xlc.community.community.model")
class CommunityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired(required=true)
	private UserMapper userMapper;

	@Test
	public void insert(){

		User user = new User();
		user.setToken(UUID.randomUUID().toString());
		user.setName("xlc");
		user.setAccountId("123366455");
		user.setGmtCreate(new Date());
		user.setGmtModified(user.getGmtCreate());
		this.userMapper.insert(user);

	}

}
