package com.shuaigef.springsecuritydemo.controller;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import com.shuaigef.springsecuritydemo.common.response.BaseResponse;
import com.shuaigef.springsecuritydemo.common.utils.ResultUtils;
import com.shuaigef.springsecuritydemo.exception.BusinessException;
import com.shuaigef.springsecuritydemo.model.dto.role.RoleAddRequest;
import com.shuaigef.springsecuritydemo.model.entity.Role;
import com.shuaigef.springsecuritydemo.model.vo.RoleVO;
import com.shuaigef.springsecuritydemo.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理-角色管理接口
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Api(tags = "系统管理-角色管理")
@Slf4j
@RestController
@RequestMapping("/manage/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 新增角色
     *
     * @param roleAddRequest
     * @return
     */
    @ApiOperation("新增角色")
    @PreAuthorize("@roleCheckService.hasPermission('systemManage:roleManage')")
    @PostMapping
    public BaseResponse addRole(@Valid @RequestBody RoleAddRequest roleAddRequest) {
        long roleId = roleService.saveRole(roleAddRequest);
        return ResultUtils.success(roleId);
    }

    /**
     * 根据 id 查询角色
     *
     * @param id
     * @return
     */
    @ApiOperation("根据 id 查询角色")
    @GetMapping("/vo")
    public BaseResponse<RoleVO> getRoleVOById(@RequestParam @Min(1) long id) {
        Role role = roleService.getById(id);
        if (role == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该角色不存在");
        }
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role, roleVO);
        return ResultUtils.success(roleVO);
    }
}
