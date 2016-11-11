package com.teamacronymcoders.base.util;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static void setStaticField(Class clazz, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.set(null, value);
        } catch (NoSuchFieldException e) {
            //Nothing, it'll happen.
        } catch (IllegalAccessException e) {
            Platform.attemptLogExceptionToCurrentMod(e);
        }
    }
}
