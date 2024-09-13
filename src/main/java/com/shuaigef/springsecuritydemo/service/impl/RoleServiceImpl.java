package com.shuaigef.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.mapper.RoleMapper;
import com.shuaigef.springsecuritydemo.model.dto.role.RoleAddRequest;
import com.shuaigef.springsecuritydemo.model.entity.Role;
import com.shuaigef.springsecuritydemo.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    private static final String ADMIN_ROLE_NAME = "admin";

    @Override
    public long saveRole(RoleAddRequest roleAddRequest) {
        String roleName = roleAddRequest.getRoleName();
        if (ADMIN_ROLE_NAME.equals(roleName)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能新增管理员角色");
        }
        Long count = baseMapper
                .selectCount(new LambdaQueryWrapper<Role>().eq(Role::getRoleName, roleName));
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该角色已存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleAddRequest, role);
        baseMapper.insert(role);
        return role.getId();
    }
}




