package com.shuaigef.springsecuritydemo.common.filter;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.constant.SecurityConstant;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.common.utils.JwtUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 全局 jwt 过滤器，用于校验当前请求是否需要进行 jwt 校验，以及 jwt 是否正确
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Slf4j
public class GlobalJwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public GlobalJwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpServletRequest.getRequestURI();
        if (!checkIgnoreURI(requestURI)) {
            try {
                String jwt = resolveToken(httpServletRequest);
                if (StringUtils.hasText(jwt)) {
                    // 验证 jwt 是否合法
                    jwtUtils.validateToken(jwt);
                    Authentication authentication = jwtUtils.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException e) {
                httpServletRequest.setAttribute(SecurityConstant.ERROR_MESSAGE, e);
                httpServletRequest.getRequestDispatcher("/handler/tokenError")
                        .forward(httpServletRequest, httpServletResponse);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 获取 jwt 值
     *
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(SecurityConstant.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken
                .startsWith(SecurityConstant.TOKEN_PREFIX)) {
            return bearerToken.substring(SecurityConstant.TOKEN_PREFIX.length());
        }
        throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "传入的令牌为空或格式错误");
    }

    /**
     * 当前请求是否需要被权限校验
     *
     * @param requestURI
     * @return
     */
    private boolean checkIgnoreURI(String requestURI) {
        return SecurityConstant.IGNORE_URI_PREFIX.stream().anyMatch(requestURI::startsWith);
    }
}
