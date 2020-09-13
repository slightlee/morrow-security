package com.morrow.modules.sys.enums;

/**
 * 菜单类型枚举
 *
 * @Author Tomorrow
 * @Date 2020/9/7 6:11 下午
 */
public enum MenuTypeEnum {

    /**
     * 菜单
     */
    MENU(0),
    /**
     * 按钮
     */
    BUTTON(1);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
