package com.shuaigef.springsecuritydemo.constant;


import java.util.Arrays;
import java.util.List;

/**
 * 权限校验常量
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public interface SecurityConstant {

    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    String ERROR_MESSAGE = "errorMessage";

    /**
     * 超级管理员账号
     */
    String ADMIN_USERNAME = "admin";
    Long ADMIN_USER_ID = 1L;
    /**
     * 超级管理员角色
     */
    String ADMIN_ROLE_NAME = "超级管理员";
    Long ADMIN_ROLE_ID = 1L;

    List<String> IGNORE_URI_PREFIX = Arrays.asList(
            "/api/system/login", "/api/swagger-resources", "/api/swagger-ui.html", "/api/doc.html",
            "/api/images", "/api/webjars", "/api/v2/api-docs", "/api/favicon.ico", "/handler");

    String[] PERMIT_ALL_MATCHERS =
            new String[]{"/system/login", "/swagger-resources/**", "/swagger-ui.html",
                    "/doc.html", "/images/**", "/webjars/**", "/v2/api-docs", "/favicon.ico",
                    "/handler/**"};

}
