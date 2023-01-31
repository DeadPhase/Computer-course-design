package com.zyz.bookshopmanage;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyz.bookshopmanage.Utils.CurrentTime;
import com.zyz.bookshopmanage.mapper.UserMapper;
import com.zyz.bookshopmanage.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootTest
public class TestConnection {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name","lucy");
        //从redis获取值
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testPage(){
       Page<User> page =  new Page<>(1,6);
       userMapper.selectPage(page,null);
        System.out.println(page.getTotal());
        System.out.println(page.getRecords());

    }

    //测试添加新用户
    @Test
    public void addUserTest(){
        String time = CurrentTime.getCurrentTime();

        User user = new User();
//        user.setId(1L);
        user.setUserName("张三");
        user.setPassword("123");
        user.setIdCard("4126262199911072712");
        user.setEmail("zhangsan@qq.com");
        user.setGender("女");
        user.setNickName("张二蛋");
        user.setPhone("18282939271");
        user.setRegisterTime(time);
        int rs = userMapper.insert(user);
        System.out.println(rs);


    }





}
