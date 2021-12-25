package com.tojaoomy.demo.starter.validate;

import com.tojaoomy.demo.api.support.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.List;

import static com.tojaoomy.demo.api.enums.ResultStatusEnum.PARAMETER_MISSING;

/**
 * @author 玉书
 * @date 2021/2/7
 */
@Slf4j
@Aspect
@Configuration
public class ParamValidatorAop {

    @Pointcut("@annotation(com.tojaoomy.demo.starter.validate.ParamValidate)")
    public void execute() {}

    @Around("execute()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ParamValidate paramValidate = method.getAnnotation(ParamValidate.class);
        Class returnType = paramValidate.returnType();
        Object[] args = joinPoint.getArgs();
        if(args != null && args.length > 0) {
            for(Object arg : args) {
                //校验参数
                List<String> validate = ParamValidator.validate(arg);
                if(CollectionUtils.isNotEmpty(validate)) {
                    if(returnType != null && returnType.equals(ResponseEntity.class)) {
                        return new ResponseEntity(ResultEntity.of("", PARAMETER_MISSING.getCode(), validate.toString()), HttpStatus.OK);
                    }
                    return ResultEntity.of("", PARAMETER_MISSING.getCode(), validate.toString());
                }
            }
        }
        return joinPoint.proceed();
    }

}