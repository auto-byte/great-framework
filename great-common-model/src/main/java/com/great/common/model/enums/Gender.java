package com.great.common.model.enums;

import com.great.common.model.EnumMaps;

import java.util.Map;

/**
 * Created on 2021/9/18 09:56
 *
 * @author Y.X
 */
public enum Gender implements KeyEnum<Integer> {
    NONE(0, ""),
    MAN(1, "男"),
    WOMAN(2, "女"),
    DOUBLE(3, "其他"),
    ;

    private static final Map<Integer, Gender> enumMap = EnumMaps.keyMap(Gender.class);
    private final int value;
    private final String label;

    Gender(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Gender getByValue(Integer value) {
        return enumMap.getOrDefault(value, Gender.NONE);
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public Integer getKey() {
        return getValue();
    }
}
