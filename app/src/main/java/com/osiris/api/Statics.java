package com.osiris.api;

import com.osiris.api.model.RSClass;
import com.osiris.api.model.RSField;

public class Statics extends RSClass {
    private RSField localPlayer;

    public Statics() {
        try {
            rsClass = Class.forName("ie");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Player getLocalPlayer() {
        localPlayer = new RSField(this, "jp", rsClass);
        Player localPlayer = new Player();
        localPlayer.reference = this.localPlayer.getValue();
        return localPlayer;
    }

}
