package com.shuaigef.springsecuritydemo.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限对象附加信息
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ApiModel("权限对象附加信息")
@Data
public class SysAuthorityMeta {

    /**
     * 是否隐藏路由菜单
     */
    @ApiModelProperty(value = "是否隐藏路由菜单")
    private Boolean hideMenu;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 组件名称
     */
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    /**
     * 页面名称
     */
    @ApiModelProperty(value = "页面名称")
    private String title;

}
