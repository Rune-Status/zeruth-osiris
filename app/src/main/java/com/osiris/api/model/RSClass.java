package com.osiris.api.model;

public class RSClass {

    String className;
    protected Object rsClass;
    public Object reference;

    protected RSClass() {
    }

    public RSClass(Object reference) {
        this.reference = reference;
    }
}
