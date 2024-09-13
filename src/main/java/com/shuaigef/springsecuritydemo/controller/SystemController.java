package com.shuaigef.springsecuritydemo.controller;

import com.shuaigef.springsecuritydemo.common.response.BaseResponse;
import com.shuaigef.springsecuritydemo.common.utils.JwtUtils;
import com.shuaigef.springsecuritydemo.common.utils.ResultUtils;
import com.shuaigef.springsecuritydemo.common.utils.SecurityUtils;
import com.shuaigef.springsecuritydemo.constant.SecurityConstant;
import com.shuaigef.springsecuritydemo.model.dto.user.UserLoginRequest;
import com.shuaigef.springsecuritydemo.model.entity.Authority;
import com.shuaigef.springsecuritydemo.model.entity.SessionUser;
import com.shuaigef.springsecuritydemo.model.vo.LoginUserVO;
import com.shuaigef.springsecuritydemo.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理接口
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Api(tags = "系统管理")
@Slf4j
@RestController
@RequestMapping("/system")
public class SystemController {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthorityService authorityService;

    /**
     * 登录接口
     *
     * @param userLoginRequest
     * @return
     */
    @ApiOperation("登录接口-获取token")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginUserVO>> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(),
                        userLoginRequest.getPassword());
        Authentication authentication = this.authenticationManager
                .authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.TOKEN_HEADER, "Bearer " + jwt);
        // 获取权限树
        long currentUserId = SecurityUtils.getCurrentUserId();
        List<Authority> authorityList = authorityService.findMenuTree(currentUserId);
        SessionUser sessionUser =
                (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(
                ResultUtils.success(new LoginUserVO(jwt, sessionUser, authorityList)),
                httpHeaders, HttpStatus.OK);
    }

}
