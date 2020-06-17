/*
Copyright 2020 Null(zeruth)

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.osiris.api;

import com.osiris.api.model.RSClass;
import com.osiris.api.model.RSField;

public class Tile extends RSClass {
    private RSField gameObjectsField;
    private RSField xField;
    private RSField yField;
    private RSField planeField;

    Tile() {
        try {
            rsClass = Class.forName("bq");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GameObject[] getGameObjects() {
        gameObjectsField = new RSField(this, "ao", this.reference);
        Object[] gameObjectCache = (Object[]) gameObjectsField.getValue();
        if (gameObjectCache != null)
        {
            GameObject[] gameObjects = new GameObject[gameObjectCache.length];
            int i = 0;
            for (Object o : gameObjectCache)
            {
                GameObject go = new GameObject();
                go.reference = o;
                gameObjects[i] = go;
                i++;
            }
            return gameObjects;
        }
        else return null;
    }

    public int getX() {
        xField = new RSField(this, "ag", this.reference);
        int i = (int) xField.getValue();
        i *= -965266879;
        return i;
    }

    public int getY() {
        yField = new RSField(this, "am", this.reference);
        int i = (int) yField.getValue();
        i *= 764437761;
        return i;
    }

    public int getPlane() {
        planeField = new RSField(this, "ah", this.reference);
        int i = (int) planeField.getValue();
        i *= 1397422987;
        return i ;
    }
}
