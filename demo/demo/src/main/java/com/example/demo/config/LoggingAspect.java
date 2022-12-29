package com.example.demo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.UUID;


/**
 * Aspect for logging execution of service and repository Spring components.
 * @author Ramesh Fadatare
 *
 */
//@Slf4j
//@Aspect
//@Component
//public class LoggingAspect {
//
////    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    /**
//     * Pointcut that matches all repositories, services and Web REST endpoints.
//     */
//    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
//            " || within(@org.springframework.stereotype.Service *)" +
//            " || within(@org.springframework.web.bind.annotation.RestController *)")
//    public void springBeanPointcut() {
//        // Method is empty as this is just a Pointcut, the implementations are in the advices.
//    }
//
//    /**
//     * Pointcut that matches all Spring beans in the application's main packages.
//     */
//    @Pointcut("within(com.example.demo..*)" +
//            " || within(com.example.demo.service..*)" +
//            " || within(com.example.demo.web.rest..*)")
//    public void applicationPackagePointcut() {
//        // Method is empty as this is just a Pointcut, the implementations are in the advices.
//    }
//
//    /**
//     * Advice that logs methods throwing exceptions.
//     *
//     * @param joinPoint join point for advice
//     * @param e exception
//     */
//    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
//    }
//
//    /**
//     * Advice that logs when a method is entered and exited.
//     *
//     * @param joinPoint join point for advice
//     * @return result
//     * @throws Throwable throws IllegalArgumentException
//     */
//    @Around("applicationPackagePointcut() && springBeanPointcut()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        if (log.isDebugEnabled()) {
//            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
//        }
//        try {
//            Object result = joinPoint.proceed();
//            if (log.isDebugEnabled()) {
//                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
//                        joinPoint.getSignature().getName(), result);
//            }
//            return result;
//        } catch (IllegalArgumentException e) {
//            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
//                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//            throw e;
//        }
//    }
//}

@Aspect
@Component
@Configuration
public class LoggingAspect {
    private final ObjectMapper obj = JsonMapper.builder().addModule(new JavaTimeModule()).build().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String requestId = "";



    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut(" within(@org.springframework.web.bind.annotation.RestController *)")
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut(" within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void applicationPackagePointcut1() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }




    @AfterThrowing(pointcut = "applicationPackagePointcut1()", throwing = "e")
    public void logAfterThrowing( Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        log.error("------>Call api [{}] has error : {}", request.getRequestURI(), e.getMessage());
    }


    //log req truoc khi vao 1 ham
    @Before("applicationPackagePointcut()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {
        requestId = UUID.randomUUID().toString();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    continue;
                }
                log.info("Start call api  [{}] \n [{}]\n request: {}", request.getRequestURI(), requestId, obj.writeValueAsString((arg)));
            }
        }

        log.info("Start call api Get  [{}] \n [{}]\n request: {}", request.getRequestURI(), requestId, "null");
    }

    //log res sau khi ket thuc ham
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(Object result) throws JsonProcessingException {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        log.info("Response from api  [{}] \n[{}]\n response: {}", request.getRequestURI(), requestId, obj.writeValueAsString(result));
    }
}
