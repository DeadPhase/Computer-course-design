package com.example.zheng.service;

import com.example.zheng.pojo.Syslog;

public interface SysLogService {

    //写入日志
    int addLog(Syslog syslog);

}
