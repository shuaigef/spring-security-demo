package com.shuaigef.springsecuritydemo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色权限表
 *
 * @TableName role_authority
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ApiModel("角色权限表对象")
@TableName(value = "role_authority")
@Data
public class RoleAuthority implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Long authorityId;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
