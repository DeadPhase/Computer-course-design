package com.example.zheng.service.impl;

import com.example.zheng.mapper.SysLogMapper;
import com.example.zheng.pojo.Syslog;
import com.example.zheng.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    //写入日志
    @Override
    public int addLog(Syslog syslog) {
        return sysLogMapper.addLog(syslog);
    }
}
