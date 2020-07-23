package com.xlc.community.community;

import com.xlc.community.community.mapper.LogInfoMapper;
import com.xlc.community.community.mapper.UserMapper;
import com.xlc.community.community.model.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
@MapperScan(basePackages = "com.xlc.community.community.model")
class CommunityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired(required=true)
	private UserMapper userMapper;

	@Autowired
    private RedisTemplate redisTemplate;


	@Autowired
	private LogInfoMapper mapper;


	@Test
	public void insert(){

		User user = new User();
		user.setToken(UUID.randomUUID().toString());
		user.setName("xlc");
//		user.setAccountId("123366455");
//		user.setGmtCreate(new Date());
//		user.setGmtModified(user.getGmtCreate());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("getHubUser", user);

        //this.userMapper.insert(user);

	}

   @Test
	public  void  testRedis(){
        Boolean getHubUser = redisTemplate.hasKey("getHubUser");
        if (getHubUser == true){
            User user  =(User) redisTemplate.opsForValue().get("getHubUser");
            System.out.println(user.getName());
        }
    }




}
