package com.sky.aspect;
import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Slf4j
@Component
public class AutoFillAspect {
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void servicePointcut() {}
    @Before("servicePointcut()")
        public void autoFill(JoinPoint joinPoint) {
        log.info("AutoFillAspect start!");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        OperationType operationType = annotation.value();
        Object entity = args[0];
        LocalDateTime now = LocalDateTime.now();
        Long currentUserId = BaseContext.getCurrentId();
        if(operationType==OperationType.INSERT){
            try{
                Method method1 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method method2 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method method3 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method method4 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //通过反射调用方法，设置属性值
                method1.invoke(entity,now);
                method2.invoke(entity,currentUserId);
                method3.invoke(entity,now);
                method4.invoke(entity,currentUserId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if(operationType==OperationType.UPDATE){
            try{
                Method method3 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method method4 = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                //通过反射调用方法，设置属性值
                method3.invoke(entity,now);
                method4.invoke(entity,currentUserId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
