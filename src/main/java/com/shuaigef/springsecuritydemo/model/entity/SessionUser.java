package com.shuaigef.springsecuritydemo.model.entity;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 扩展 Security 默认的 user 信息
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ToString
@Setter
@Getter
public class SessionUser extends User {

    private static final long serialVersionUID = -1L;

    private Long userId;

    // 登录方式 1 - 登录方式1 2 - 登录方式2
    private Integer type;

    private Long roleId;

    private String nickname;

    private String userAvatar;

    public SessionUser(String username, String password,
            Collection<? extends GrantedAuthority> authorities,
            Long userId, Integer type, Long roleId, String nickname, String userAvatar) {
        super(username, password, authorities);
        this.userId = userId;
        this.type = type;
        this.roleId = roleId;
        this.nickname = nickname;
        this.userAvatar = userAvatar;
    }

    /**
     * Returns {@code true} if the supplied object is a {@code SecurityUser} instance with the same
     * {@code username} value.
     * <p>
     * In other words, the objects are equal if they have the same username, representing the same
     * principal.
     */
    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof SessionUser && userId.equals(((SessionUser) rhs).userId);
    }

    /**
     * Returns the hashcode of the {@code username}.
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }

}
