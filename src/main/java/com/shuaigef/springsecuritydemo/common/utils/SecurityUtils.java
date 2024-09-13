package com.shuaigef.springsecuritydemo.common.utils;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.model.entity.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * security 工具类
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public final class SecurityUtils {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static SessionUser getCurrentUser() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                try {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    return (SessionUser) springSecurityUser;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "请重新登录");
    }

    /**
     * 获取当前登录用户 id
     *
     * @return
     */
    public static long getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @return
     */
    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    private static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    private SecurityUtils() {
    }

}
