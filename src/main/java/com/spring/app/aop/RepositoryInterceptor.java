package com.spring.app.aop;

import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.exceptions.RepositoryInterceptorException;

@Aspect
@EnableAspectJAutoProxy
@Component
public class RepositoryInterceptor {

    @Autowired
    private ObjectMapper objectMapper;
    
    @Pointcut("execution(* com.spring.app.services.*RepositoryService.update(..))")
    public void repositoryPointcut() {

    }

    @Before("repositoryPointcut()")
    public void repositoryInterceptorResult(JoinPoint joinPoint) throws IOException {
        Object value = joinPoint.getArgs()[0];

        String json = objectMapper.writeValueAsString(value);
        JsonNode node = objectMapper.readTree(json);

        int id = node.get("id").intValue();

        if (id == 0) {
            throw new RepositoryInterceptorException("The Update() method's requires ID value present in the entity.");
        }
    }
}
