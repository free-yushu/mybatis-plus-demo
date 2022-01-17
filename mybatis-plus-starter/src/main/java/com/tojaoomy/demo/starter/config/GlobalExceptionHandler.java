package com.tojaoomy.demo.starter.config;

import com.alibaba.fastjson.JSON;
import com.tojaoomy.demo.api.enums.ResultStatusEnum;
import com.tojaoomy.demo.api.support.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLTransientConnectionException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 *
 * @date 2021/12/31
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity handlerException(BindException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestId = request.getParameter("requestId");

        List<FieldError> allErrors = e.getFieldErrors();
        List<String> messageList = allErrors.stream().map(error -> error.getField() + error.getDefaultMessage()).collect(Collectors.toList());
        log.error("<<< uri [{}] error", requestURI, e);
        return new ResponseEntity(ResultEntity.of(requestId, ResultStatusEnum.PARAMETER_MISSING.getCode(), JSON.toJSONString(messageList)), HttpStatus.OK);
    }

    @ExceptionHandler(SQLTransientConnectionException.class)
    public ResponseEntity handlerException(SQLTransientConnectionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestId = request.getParameter("requestId");
        log.error("<<< uri [{}] SQLTransientConnectionException error", requestURI, e);
        return new ResponseEntity(ResultEntity.of(requestId, ResultStatusEnum.SYSTEM_INTERNAL_ERROR.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handlerException(Throwable e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String requestId = request.getParameter("requestId");

        log.error("<<< uri [{}] error", requestURI, e);
        return new ResponseEntity(ResultEntity.of(requestId, ResultStatusEnum.SYSTEM_INTERNAL_ERROR.getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}