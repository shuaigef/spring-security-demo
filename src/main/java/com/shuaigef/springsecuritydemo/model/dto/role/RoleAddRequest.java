package com.shuaigef.springsecuritydemo.model.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 新增角色请求
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@ApiModel("新增角色请求参数")
@Data
public class RoleAddRequest implements Serializable {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", required = true)
    @NotBlank(message = "角色描述不能为空")
    private String roleDesc;

    /**
     * 角色类型
     */
    @ApiModelProperty(value = "角色类型", required = true)
    @NotBlank(message = "角色类型不能为空")
    private String roleType;

    private static final long serialVersionUID = 1L;

}
