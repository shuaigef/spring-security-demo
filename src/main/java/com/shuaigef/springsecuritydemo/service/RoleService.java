package com.shuaigef.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuaigef.springsecuritydemo.model.dto.role.RoleAddRequest;
import com.shuaigef.springsecuritydemo.model.entity.Role;

/**
 * 角色服务
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public interface RoleService extends IService<Role> {

    /**
     * 新增角色
     * @param roleAddRequest
     * @return
     */
    long saveRole(RoleAddRequest roleAddRequest);

}
