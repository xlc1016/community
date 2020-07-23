package com.xlc.community.community.advice;

import com.xlc.community.community.dto.QuestionDTO;
import com.xlc.community.community.mapper.LogInfoMapper;
import com.xlc.community.community.model.LogInfo;
import com.xlc.community.community.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 *@创建人 xlc
 *@创建时间 2020-7-22
 *@描述 日志切面
 **/
@Component
@Aspect
public class LogAspect {

    @Autowired
    private LogInfoMapper logInfoMapper;// 日志接口


    @Pointcut("execution(* com.xlc.community.community.controller.AuthorController.*(..))")
    public void webLog(){ }

//
//    @Pointcut("execution(* com.xlc.community.community.interecption.SesssionInterception.preHandle(..))")
//    public void webLogLogin(){};

    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Object object = new Object();
        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        // 获取 user 对象
        User user =(User)session.getAttribute("user");
        try {

            object = point.proceed(); // 证明方法已经执行
            User user1 = (User)request.getSession().getAttribute("user");
            String name = point.getSignature().getName();

            if ("logout".equals(name)){
                LogInfo logInfo  = new LogInfo();
                //  退出
                if (!StringUtils.isEmpty(user)){
                    logInfo.setLoginName(user.getName());
                }
                if (StringUtils.isEmpty(user1)){
                    // sessioon 注销退出成功
                    logInfo.setOperState("退出成功");
                }else{
                    logInfo.setOperState("退出失败");
                }
                logInfo.setOperName("退出系统");
                logInfo.setCreateTime(new Date());
                int i = logInfoMapper.insert(logInfo);
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            return object;
        }


    }

    // 登录
//    @Around("webLogLogin()")
//    public Object araundLogIn(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        Object object = new Object();
//        //获取request对象
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes())
//                .getRequest();
//        HttpSession session = request.getSession();
//
//
//        try {
//            object =   joinPoint.proceed();
//            // 获取 user 对象
//            User user =(User)session.getAttribute("user");
//
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        } finally {
//
//            return  object;
//        }
//
//    }

    public <T> T getArgs(ProceedingJoinPoint joinPoint,T t) throws Exception{
        //String classType = joinPoint.getTarget().getClass().getName();
        //String methodName = joinPoint.getSignature().getName();
        // 参数值
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if(t.getClass().isAssignableFrom(o.getClass())){
                return  (T) o;
            }
        }
        return null;
    }

}
