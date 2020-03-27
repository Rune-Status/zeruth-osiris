package com.osiris.api.model;

import java.lang.reflect.Field;

public class RSField {

    private RSClass parentClass;
    private Field field;
    public Object reference;

    public RSField(RSClass parentClass, String field, Object reference) {
        try {
            this.parentClass = parentClass;
            this.field = ((Class)(parentClass.rsClass)).getDeclaredField(field);
            this.reference = reference;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to access Class:" + parentClass.className + " Field:" + field);
        }
    }

    public Object getValue() {
        try {
            field.setAccessible(true);
            Object value = field.get(reference);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Failed to access Class:" + parentClass.className + " Field:" + field.getName());
    }
}
