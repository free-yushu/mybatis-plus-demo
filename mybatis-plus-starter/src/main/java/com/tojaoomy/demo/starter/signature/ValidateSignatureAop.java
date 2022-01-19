package com.tojaoomy.demo.starter.signature;

import com.tojaoomy.demo.api.dto.BaseRequest;
import com.tojaoomy.demo.api.support.ResultEntity;
import com.tojaoomy.demo.infra.sign.config.SignatureProperties;
import com.tojaoomy.demo.starter.util.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static com.tojaoomy.demo.api.enums.ResultStatusEnum.REQUEST_SIGN_NOT_VALID;


/**
 * @author 玉书
 * @date 2021/2/7
 */
@Slf4j
@Aspect
@Configuration
public class ValidateSignatureAop {

    @Autowired
    private SignatureProperties signProperties;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) ")
    public void controllerClass() {}

    @Around(value = "controllerClass() && args(request, ..)", argNames = "joinPoint, request")
    public Object doAround(ProceedingJoinPoint joinPoint, BaseRequest request) throws Throwable {
        if(signProperties.getPayment().isNeedCheckSign()) {
            if(ObjectUtils.anyNull(request.getAppkey(), request.getTs(), request.getSign())) {
                return ResultEntity.buildParameterMissing(request.getRequestId(), "appKey,ts,sign");
            } else {
                String generateSign = SignatureUtil.generateSign(request, signProperties.getPayment().getSecretKey());
                if(!request.getSign().equals(generateSign)) {
                    if(signProperties.getPayment().isDebugSign()) {
                        return ResultEntity.of(request.getRequestId(), REQUEST_SIGN_NOT_VALID.getCode(), generateSign);
                    } else {
                        return ResultEntity.of(request.getRequestId(), REQUEST_SIGN_NOT_VALID);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}