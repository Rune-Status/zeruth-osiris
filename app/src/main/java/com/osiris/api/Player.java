package com.osiris.api;

import com.osiris.api.model.RSClass;
import com.osiris.api.model.RSField;

public class Player extends RSClass {
    private RSField username;

    Player() {
        try {
            rsClass = Class.forName("hm");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Username getUsername() {
        username = new RSField(this, "aj", this.reference);
        Username username = new Username();
        username.reference = this.username.getValue();
        return username;
    }
}
