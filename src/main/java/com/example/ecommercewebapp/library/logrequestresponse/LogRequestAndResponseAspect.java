package com.example.ecommercewebapp.library.logrequestresponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

/**
 * @see Log
 *
 * @author Ahmet Emin Hergüner
 */
@Component
@Aspect
@Slf4j
public class LogRequestAndResponseAspect {

    @Pointcut("@within(com.example.ecommercewebapp.library.logrequestresponse.Log) && !@annotation(com.example.ecommercewebapp.library.logrequestresponse.Log)")
    public void logPointcut() {

    }

    @Pointcut("@annotation(com.example.ecommercewebapp.library.logrequestresponse.Log)")
    public void logPointcut2() {

    }

    @Around("logPointcut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (getClassRequest(joinPoint)) {
            logBefore(joinPoint, ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        }
        var result = joinPoint.proceed();
        if (Objects.nonNull(result) && getClassResponse(joinPoint)) {
            log.info("Method( " + joinPoint.getSignature().getName() + " ) Return value : " + result);
        }
        return result;
    }

    @Around("logPointcut2()")
    public Object logExecutionTime2(ProceedingJoinPoint joinPoint) throws Throwable {
        if (getRequest(joinPoint)) {
            logBefore(joinPoint, ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest());
        }
        var result = joinPoint.proceed();
        if (Objects.nonNull(result) && getResponse(joinPoint)) {
            log.info("Method( " + joinPoint.getSignature().getName() + " ) Return value : " + result);
        }
        return result;
    }

    public void logBefore(JoinPoint joinPoint, HttpServletRequest request) {
        log.info("Path: " + request.getServletPath() + " ; Method: " + joinPoint.getSignature().getName() + " ; Arguments:  " + Arrays.toString(joinPoint.getArgs()));
    }

    private boolean getRequest(JoinPoint joinPoint) {
        return getAnnotation(joinPoint).request();
    }

    private boolean getResponse(JoinPoint joinPoint) {
        return getAnnotation(joinPoint).response();
    }

    private boolean getClassRequest(JoinPoint joinPoint) {
        return getClassAnnotation(joinPoint).request();
    }

    private boolean getClassResponse(JoinPoint joinPoint) {
        return getClassAnnotation(joinPoint).response();
    }

    private Log getAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(Log.class);
    }

    private Log getClassAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return (Log) signature.getDeclaringType().getAnnotation(Log.class);
    }
}
