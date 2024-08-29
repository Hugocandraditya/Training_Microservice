package com.example.book_store.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Around("@annotation(LogData)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        long startTime = System.currentTimeMillis();
        log.info("Request: {} {}", request.getMethod(), request.getRequestURI());
        Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        if (result instanceof ResponseEntity<?>) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            if(((ResponseEntity<?>) result).getStatusCode().is2xxSuccessful())
                log.info("Response: Status {} - Body: {} executed in {} ms", responseEntity.getStatusCode(), responseEntity.getBody(), (endTime - startTime));
            else
                log.error("Response: Status {} - Body: {} executed in {} ms", responseEntity.getStatusCode(), responseEntity.getBody(), (endTime - startTime));
        }
        return proceed;
    }
}