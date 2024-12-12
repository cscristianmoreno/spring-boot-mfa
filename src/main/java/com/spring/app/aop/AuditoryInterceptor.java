package com.spring.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.spring.app.entity.Auditory;
import com.spring.app.services.AuditoryRepositoryService;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuditoryInterceptor {

    @Autowired
    private AuditoryRepositoryService auditoryRepositoryService;

    @Pointcut("execution(* com..*RepositoryService.*(..))")
    public void repositoriesAuditingPointcut() {

    }

    @AfterReturning("repositoriesAuditingPointcut()") 
    public void repositoryAuditingInterceptor(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();

        String className = clazz.getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        if (className.toLowerCase().contains("auditory")) {
            return;
        }

        Auditory auditory = new Auditory();
        auditory.setClassName(className);
        auditory.setMethodName(methodName);

        auditoryRepositoryService.save(auditory);
    }

    @AfterThrowing(value = "repositoriesAuditingPointcut", throwing = "exception")
    public void repositoryAuditingInterceptorException(JoinPoint joinPoint, Exception exception) {
        Class<?> clazz = joinPoint.getTarget().getClass();

        String className = clazz.getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        String verifyRepository = className.toLowerCase();

        if (verifyRepository.contains("auditory") || verifyRepository.contains("account") || 
        verifyRepository.contains("google")) {
            return;
        }

        Auditory auditory = new Auditory();
        auditory.setClassName(className);
        auditory.setMethodName(methodName);
        auditory.setException(exception.getMessage());

        auditoryRepositoryService.save(auditory);
    }
}
