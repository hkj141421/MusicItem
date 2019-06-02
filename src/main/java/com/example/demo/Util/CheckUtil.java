package com.example.demo.Util;

import java.lang.reflect.Field;

/**
 * Created by forget on 2019/3/12.
 */
public class CheckUtil {
    public static boolean isEmpry(String str) {
        if (str == null || str == "") {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 校验对象属性是否有空值
     *
     * @param object
     * @return
     */
    public static boolean isAttrEmpty(Object object) {

        try {
            Class clz = object.getClass();
            Field field[] = clz.getDeclaredFields();
            for (Field f : field) {
                f.setAccessible(true);
                Object value = f.get(object);
                if (value == null || value == "") {
                    return true;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
}
