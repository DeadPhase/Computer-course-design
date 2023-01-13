package com.zheng.yu.controller;

import com.zheng.yu.pojo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Api(value = "学生信息模块", tags = "学生信息模块")
@RestController
public class StudentHandler {

    @Autowired
    private RedisTemplate redisTemplate;//操作redis数据库，相当于mybaitis中的session

    /**
     * @param student
     * @RequestBody：作用是将客户端传过来的json转化为java对象
     */
    @ApiOperation(value = "学生信息对象", notes = "学生对象")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Student")
    )
    @PostMapping("/set")
    public void set(@RequestBody Student student) {
        redisTemplate.opsForValue().set("student", student);

    }

    @ApiOperation(value = "根据key查询学生的信息", notes = "根据key查询某一个用户的信息")
    @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String")
    @GetMapping("/get/{key}")
    public Student get(HttpSession session, @PathVariable("key") String key) {

        return (Student) redisTemplate.opsForValue().get(key);

    }

    @ApiOperation(value = "根据key删除学生的信息", notes = "根据key删除某一个用户的信息")
    @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String")
    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable("key") String key) {
        redisTemplate.delete(key);
        return redisTemplate.hasKey(key);//判断key是否存在。不存在返回false
    }

    //测试操作redis的5种数据类型
    @GetMapping("/string")
    public String stringTest() {
        redisTemplate.opsForValue().set("str", "Hello World");
        String str = (String) redisTemplate.opsForValue().get("str");
        return str;
    }

    @GetMapping("/list")
    public List<String> listTest() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("list", "Hello");
        listOperations.leftPush("list", "World");
        listOperations.leftPush("list", "java");
        List<String> list = listOperations.range("list", 0, 2);
        return list;
    }

    @GetMapping("/set")
    public Set<String> setTest() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        setOperations.add("set", "Hello");
        setOperations.add("set", "Hello");
        setOperations.add("set", "Java");
        Set<String> set = setOperations.members("set");
        return set;
    }

    @GetMapping("/zset")
    public Set<String> zsetTest() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset", "Hello", 3);
        zSetOperations.add("zset", "World", 2);
        zSetOperations.add("zset", "Python", 1);
        Set<String> set = zSetOperations.range("zset", 0, 2);
        return set;
    }

    @GetMapping("/hashTest")
    public void hashTest() {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("key", "hashkey", "hello");
        System.out.println(hashOperations.get("key", "hashkey"));
    }


}
