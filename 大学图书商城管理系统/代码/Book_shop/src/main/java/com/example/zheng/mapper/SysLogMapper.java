package com.example.zheng.mapper;


import com.example.zheng.pojo.Syslog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface SysLogMapper {
    //写入日志
    int addLog(Syslog syslog);
}
