package com.wei.eduyang;

import com.wei.eduyang.domain.User;
import com.wei.eduyang.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduYangApplicationTests {

    @Autowired
    private UserMapper userMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void userMapperTest(){
        User user = userMapper.findUserByName("wei");
        System.out.println(user);
    }

}
