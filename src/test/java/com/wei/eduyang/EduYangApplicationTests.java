package com.wei.eduyang;

import com.wei.eduyang.domain.Plan;
import com.wei.eduyang.domain.Tag;
import com.wei.eduyang.domain.User;
import com.wei.eduyang.mapper.PlanMapper;
import com.wei.eduyang.mapper.TagMapper;
import com.wei.eduyang.mapper.UserMapper;
import net.bytebuddy.asm.Advice;
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
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private TagMapper tagMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void userMapperTest(){
        User user = userMapper.findUserByName("wei");
        System.out.println(user);
        Plan plan = planMapper.getPlan();
        Tag tag = tagMapper.getTag();

        System.out.println(plan);
        System.out.println(tag);
    }

}
