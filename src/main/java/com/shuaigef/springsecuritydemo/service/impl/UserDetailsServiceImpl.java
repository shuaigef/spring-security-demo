package com.shuaigef.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shuaigef.springsecuritydemo.mapper.UserMapper;
import com.shuaigef.springsecuritydemo.model.entity.SessionUser;
import com.shuaigef.springsecuritydemo.model.entity.User;
import java.util.ArrayList;
import java.util.Optional;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * security 自定义 UserDetailsService 实现
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        log.debug("Authenticating {}", username);
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        return Optional.ofNullable(user)
                .map(userTemp -> new SessionUser(username, userTemp.getPassword(),
                        new ArrayList<>(),
                        userTemp.getId(), 1, userTemp.getRoleId(), userTemp.getNickname(),
                        userTemp.getUserAvatar()))
                .orElseThrow(() -> new UsernameNotFoundException("登录信息错误"));
    }

}
