package com.osiris.api;

import com.osiris.api.model.RSClass;
import com.osiris.api.model.RSField;

public class Username extends RSClass {
    private RSField name1;
    private RSField name2;

    Username() {
        try {
            rsClass = Class.forName("eo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName1() {
        name1 = new RSField(this, "aj", reference);
        if (name1.getValue() != null)
            return (String) name1.getValue();
        return null;
    }

    public String getName2() {
        name2 = new RSField(this, "ax", reference);
        if (name2.getValue() != null)
            return (String) name2.getValue();
        return null;
    }
}
