package com.shuaigef.springsecuritydemo.common.utils;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.model.entity.SessionUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * jwt 工具类
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Component
@Slf4j
public class JwtUtils {

    private static final String USERNAME = "username";
    private static final String NICKNAME = "nickname";
    /* 1 登录方式1 2 登录方式2 */
    private static final String TYPE = "type";
    private static final String ROLE_ID = "roleId";
    private static final String USER_AVATAR = "userAvatar";

    // 7天，秒为单位
    private static final long EXPIRE = 604800000l;

    private static final String JWT_SECRET = "shuaigef";

    /**
     * 生成密钥
     *
     * @return
     */
    public static SecretKey generalKey() {
        // 本地的密码解码
//    byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        byte[] encodedKey = Base64.getDecoder().decode(JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 生成token 如果更改sessionUser 需要一起改了生成和解析token的方法
     */
    public String createToken(Authentication authentication) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now + EXPIRE);

        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();

        return Jwts.builder()
                // 用户ID
                .setSubject(sessionUser.getUserId() + "")
                // 自定义信息 对应sessionUser中的扩展信息
                .claim(USERNAME, sessionUser.getUsername())
                .claim(TYPE, 1)
                .claim(ROLE_ID, sessionUser.getRoleId())
                .claim(NICKNAME, sessionUser.getNickname())
                .claim(USER_AVATAR, sessionUser.getUserAvatar())
                // 签名信息
                .signWith(SignatureAlgorithm.HS512, generalKey()).setExpiration(validity)
                // 到期时间
                .compact();
    }

    /**
     * 生成token 如果更改sessionUser 需要一起改了生成和解析token的方法
     */
    public String createThirdToken(Authentication authentication) {
        long now = System.currentTimeMillis();
        Date validity = new Date(now + EXPIRE);

        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();

        return Jwts.builder()
                // 用户ID
                .setSubject(sessionUser.getUserId() + "")
                // 自定义信息 对应sessionUser中的扩展信息
                .claim(USERNAME, sessionUser.getUsername())
                .claim(TYPE, 2)
                .claim(ROLE_ID, sessionUser.getRoleId())
                .claim(NICKNAME, sessionUser.getNickname())
                .claim(USER_AVATAR, sessionUser.getUserAvatar())
                // 签名信息
                .signWith(SignatureAlgorithm.HS512, generalKey()).setExpiration(validity)
                // 到期时间
                .compact();
    }

    /**
     * 解析token
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();

        String username = claims.get(USERNAME) + "";
        Integer type = Integer.valueOf(claims.get(TYPE) + "");
        Long roleId = Long.valueOf(claims.get(ROLE_ID) + "");
        String nickname = claims.get(NICKNAME) + "";
        String userAvatar = claims.get(USER_AVATAR) + "";
        long userId = Long.parseLong(claims.getSubject());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + username.toUpperCase()));
        SessionUser principal = new SessionUser(username, "", authorityList, userId, type, roleId,
                nickname, userAvatar);
        return new UsernamePasswordAuthenticationToken(principal, token, authorityList);
    }

    /**
     * 校验token
     */
    public void validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(authToken);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "无效的JWT令牌！");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "过期的JWT令牌！");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "不支持的JWT令牌！");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "该JWT令牌无效！");
        }
    }
}
