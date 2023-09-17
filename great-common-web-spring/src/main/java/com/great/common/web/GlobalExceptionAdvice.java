package com.greate.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * 全局异常处理
 *
 * @author Y.X
 * @since 2021/9/7
 */
@RestControllerAdvice
@ConditionalOnProperty(
        prefix = "global.web.exception",
        value = "enable",
        havingValue = "true",
        matchIfMissing = true)
public class GlobalExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * 是否将系统异常信息作为返回的message
     */
    @Value("${global.web.exception.report-exception-message:false}")
    private boolean reportExceptionMessage;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public Result<Void> exceptionHandler(
            HttpServletRequest request, Exception e) {
        log.error("{} {} 系统异常", request.getMethod(), request.getRequestURI(), e);
        return Results.error(PlainHttpStatus.INTERNAL_SERVER_ERROR, reportExceptionMessage ? e.getMessage() : "哎呀！系统好像出了点问题 >_< ！！");
    }

    @ExceptionHandler({StatusException.class, BizException.class})
    public Result<Void> statusException(
            HttpServletRequest request, StatusException e) {
        log.warn("{} {} 处理失败, code: {} message: {}",
                request.getMethod(), request.getRequestURI(), e.getCode(), e.getMessage());
        return Results.of(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    protected Result<Object> bindException(
            HttpServletRequest request, BindException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.warn("{} {} 请求参数校验失败, message: {}",
                request.getMethod(), request.getRequestURI(), message);
        return Results.of(PlainHttpStatus.BAD_REQUEST.getCode(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected Result<Void> handleBindingResultException(
            HttpServletRequest request, MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.warn("{} {} 请求参数校验失败, message: {}",
                request.getMethod(), request.getRequestURI(), message);
        return Results.of(PlainHttpStatus.BAD_REQUEST.getCode(), message);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected Result<Void> handleMethodArgumentTypeMismatchException(
            HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String message = e.getMessage();
        log.warn("{} {} 请求参数类型错误, message: {}",
                request.getMethod(), request.getRequestURI(), message, e);
        return Results.error(PlainHttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected Result<Void> handleHttpMessageNotReadableException(
            HttpServletRequest request, HttpMessageNotReadableException e) {
        String message = e.getMessage();
        log.warn("{} {} 读取请求参数错误, message: {}",
                request.getMethod(), request.getRequestURI(), message, e);
        return Results.error(PlainHttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({MethodNotAllowedException.class})
    protected Result<Void> methodNotAllowedException(
            HttpServletRequest request, MethodNotAllowedException e) {
        String message = e.getMessage();
        log.warn("{} {} 请求姿势不正确, message: {}",
                request.getMethod(), request.getRequestURI(), message, e);
        return Results.error(PlainHttpStatus.METHOD_NOT_ALLOWED);
    }
}
