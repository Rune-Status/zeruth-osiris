package com.osiris.api;

import com.osiris.api.model.RSClass;

public class Client extends RSClass {
    private Object rsClass;

    public Client() {
        try {
            rsClass = Class.forName("client");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Client class not found.");
        }
    }
}
