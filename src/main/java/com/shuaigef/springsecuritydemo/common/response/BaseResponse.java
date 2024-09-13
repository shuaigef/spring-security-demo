package com.shuaigef.springsecuritydemo.common.response;

import com.shuaigef.springsecuritydemo.common.code.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * 通用返回类
 *
 * @param <T>
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
@Api("通用返回类")
@Data
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty("操作代码")
    private int code;

    @ApiModelProperty("响应数据")
    private T data;

    @ApiModelProperty("提示信息")
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
