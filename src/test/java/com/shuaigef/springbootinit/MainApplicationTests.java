package com.shuaigef.springbootinit;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.model.entity.Authority;
import com.shuaigef.springsecuritydemo.model.entity.Role;
import com.shuaigef.springsecuritydemo.model.entity.RoleAuthority;
import com.shuaigef.springsecuritydemo.model.entity.User;
import com.shuaigef.springsecuritydemo.model.enums.AuthorityTypeEnum;
import com.shuaigef.springsecuritydemo.service.AuthorityService;
import com.shuaigef.springsecuritydemo.service.RoleAuthorityService;
import com.shuaigef.springsecuritydemo.service.RoleService;
import com.shuaigef.springsecuritydemo.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 主类测试
 */
@SpringBootTest
@Slf4j
class MainApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private AuthorityService authorityService;

    @Resource
    private RoleAuthorityService roleAuthorityService;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用于第一次启动程序，初始化数据使用，仅执行一次即可
     */
    @Test
    void init() {
        long roleId = addAdminRole();
        // 初始密码为 123456
        long userId = addAdminUser(roleId, "123456");
        authorityInit(userId);

    }

    private long addAdminRole() {
        Role role = new Role();
        role.setRoleName("管理员");
        role.setRoleDesc("测试使用");
        role.setRoleType("1");
        boolean result = roleService.save(role);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "新增角色失败");
        }
        log.info("新增角色成功");
        return role.getId();
    }

    private long addAdminUser(long roleId, String password) {
        User user = new User();
        user.setUsername("admin");
        user.setNickname("管理员");
        user.setPassword(passwordEncoder.encode(password));
        user.setRoleId(roleId);
        boolean result = userService.save(user);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "新增用户失败");
        }
        log.info("新增用户成功");
        return user.getId();
    }

    private void authorityInit(long userId) {
        // 用户管理权限
        Authority systemManageAuthority = new Authority();
        systemManageAuthority.setCode("systemManage");
        systemManageAuthority.setMenuName("系统管理");
        systemManageAuthority.setOrderNo(1);
        systemManageAuthority.setParentId(0l);
        systemManageAuthority.setAuthorityType(AuthorityTypeEnum.MENU.getValue());
        systemManageAuthority.setRoutePath("/systemManage");
        systemManageAuthority.setHidden(0);
        boolean systemManageAuthorityResult = authorityService.save(systemManageAuthority);
        if (!systemManageAuthorityResult) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "新增系统管理权限失败");
        }

        // 用户管理权限
        Authority roleManageAuthority = new Authority();
        roleManageAuthority.setCode("systemManage:roleManage");
        roleManageAuthority.setMenuName("角色管理");
        roleManageAuthority.setOrderNo(1);
        roleManageAuthority.setParentId(systemManageAuthority.getId());
        roleManageAuthority.setAuthorityType(AuthorityTypeEnum.MENU.getValue());
        roleManageAuthority.setRoutePath("/systemManage/roleManage");
        roleManageAuthority.setHidden(0);
        boolean roleManageAuthorityResult = authorityService.save(roleManageAuthority);
        if (!roleManageAuthorityResult) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "新增用户管理权限失败");
        }

        // 管理员绑定权限
        RoleAuthority roleAuthority1 = new RoleAuthority();
        roleAuthority1.setAuthorityId(systemManageAuthority.getId());
        roleAuthority1.setRoleId(userId);
        boolean result1 = roleAuthorityService.save(roleAuthority1);
        if (!result1) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "绑定系统管理权限失败");
        }

        RoleAuthority roleAuthority2 = new RoleAuthority();
        roleAuthority2.setAuthorityId(roleManageAuthority.getId());
        roleAuthority2.setRoleId(userId);
        boolean result2 = roleAuthorityService.save(roleAuthority2);
        if (!result2) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "绑定用户管理权限失败");
        }
    }

}
