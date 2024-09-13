package com.shuaigef.springsecuritydemo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Api(tags = "系统管理-用户管理")
@RestController
@RequestMapping("/manage/user")
@Slf4j
public class UserController {

}
