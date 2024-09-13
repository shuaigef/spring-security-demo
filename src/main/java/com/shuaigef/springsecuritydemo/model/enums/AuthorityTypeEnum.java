package com.shuaigef.springsecuritydemo.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 权限类型枚举
 *
 * @author <a href="https://github.com/shuaigef">shuaigef</a>
 */
public enum AuthorityTypeEnum {

    MENU("菜单", "menu"),
    BUTTON("按钮", "button");

    private final String text;

    private final String value;

    AuthorityTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static AuthorityTypeEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (AuthorityTypeEnum anEnum : AuthorityTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;

    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
