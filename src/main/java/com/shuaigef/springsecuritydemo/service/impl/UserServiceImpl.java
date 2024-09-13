package com.shuaigef.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaigef.springsecuritydemo.mapper.UserMapper;
import com.shuaigef.springsecuritydemo.model.entity.User;
import com.shuaigef.springsecuritydemo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




