package com.shuaigef.springsecuritydemo.exception;

import com.shuaigef.springsecuritydemo.common.response.BaseResponse;
import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException：----------------{}", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException：----------------{}", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public BaseResponse handler(BadCredentialsException e) {
        log.error("用户名或密码错误：----------------{}", e.getMessage());
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public BaseResponse handler(UsernameNotFoundException e) {
        log.error("登录信息有误：----------------{}", e.getMessage());
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public BaseResponse handler(AccessDeniedException e) {
        log.error("权限不足：----------------{}", e.getMessage());
        return ResultUtils.error(ErrorCode.FORBIDDEN_ERROR);
    }

}
