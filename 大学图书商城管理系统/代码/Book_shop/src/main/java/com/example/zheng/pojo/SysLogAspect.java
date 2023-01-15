package com.example.zheng.pojo;

import com.alibaba.druid.support.json.JSONUtils;

import com.example.zheng.Utils.CurrentTime;

import com.example.zheng.Utils.UUIDutils;
import com.example.zheng.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {


    @Autowired
    private SysLogService sysLogService;


    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.example.zheng.pojo.Mylog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        Syslog sysLog = new Syslog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        Mylog myLog = method.getAnnotation(Mylog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //设置id
        String id = UUIDutils.getUUID();
        sysLog.setId(id);

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //获取时间
        String time = CurrentTime.getCurrentTime();
        sysLog.setCreateDate(time);
        //获取用户名
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        sysLog.setUsername(parent.getUsercount());


        //调用service保存SysLog实体类到数据库
        sysLogService.addLog(sysLog);
    }
}
