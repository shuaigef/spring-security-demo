package com.shuaigef.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaigef.springsecuritydemo.model.entity.Authority;
import java.util.List;

/**
 * 权限服务
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public interface AuthorityService extends IService<Authority> {

    List<Authority> findMenuTree(Long userId);

}
