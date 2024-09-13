package com.shuaigef.springsecuritydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 权限表
 *
 * @TableName authority
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ApiModel("权限表对象")
@TableName(value = "authority")
@Data
public class Authority implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限标识符
     */
    @ApiModelProperty(value = "权限标识符")
    private String code;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 菜单顺序
     */
    @ApiModelProperty(value = "菜单顺序")
    private Integer orderNo;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    private Long parentId;

    /**
     * 权限类型 menu/button
     */
    @ApiModelProperty(value = "权限类型 menu/button")
    private String authorityType;

    /**
     * 路由路径
     */
    @ApiModelProperty(value = "路由路径")
    private String routePath;

    /**
     * 是否隐藏路由菜单(0 - 否，1 - 是)
     */
    @ApiModelProperty(value = "是否隐藏路由菜单(0 - 否，1 - 是)")
    private Integer hidden;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;

    /**
     * 组件名称
     */
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;

    /**
     * 附加信息
     */
    @ApiModelProperty(value = "附加信息")
    @TableField(exist = false)
    private SysAuthorityMeta meta;

    /**
     * 子节点集合
     */
    @ApiModelProperty(value = "子节点集合")
    @TableField(exist = false)
    private List<Authority> children;

    /**
     * 此节点是否勾选
     */
    @ApiModelProperty(value = "此节点是否勾选")
    @TableField(exist = false)
    private boolean check = false;

    /**
     * 此节点是否有子节点
     */
    @ApiModelProperty(value = "此节点是否有子节点")
    @TableField(exist = false)
    private boolean node = false;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
