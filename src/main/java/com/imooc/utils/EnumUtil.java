package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * @ Author     ：nestmilk
 * @ Date       ：Created in  2018/10/8 13:32
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
