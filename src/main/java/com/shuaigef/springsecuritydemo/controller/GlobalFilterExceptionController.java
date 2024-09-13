package com.shuaigef.springsecuritydemo.controller;

import com.shuaigef.springsecuritydemo.common.response.BaseResponse;
import com.shuaigef.springsecuritydemo.common.utils.ResultUtils;
import com.shuaigef.springsecuritydemo.constant.SecurityConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * filter 全局异常处理
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ApiIgnore
@RestController
@RequestMapping("/handler")
public class GlobalFilterExceptionController {

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @GetMapping("/tokenError")
  public BaseResponse tokenError(HttpServletRequest httpServletRequest) {
    Exception errorMessage =
        (Exception) httpServletRequest.getAttribute(SecurityConstant.ERROR_MESSAGE);
    String message = errorMessage.getMessage();
    return ResultUtils.error(HttpServletResponse.SC_FORBIDDEN, message);
  }

}
