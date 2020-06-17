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

import android.util.Log;

import com.osiris.api.model.RSClass;
import com.osiris.api.model.RSField;

import java.util.ArrayList;

public class Scene extends RSClass {
    private RSField tilesCacheField;
    Object[][][] rsTiles;

    Scene() {
        try {
            rsClass = Class.forName("bn");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tile> getTiles() {
        tilesCacheField = new RSField(this, "ac", this.reference);
        ArrayList<Tile> tiles = new ArrayList<>();
        int REGION_SIZE = 104;
        rsTiles = (Object[][][]) tilesCacheField.getValue();
        int z = Client.localPlayer.getPlane();

        for (int x = 0; x < REGION_SIZE; ++x) {
            for (int y = 0; y < REGION_SIZE; ++y) {
                 Object tile = this.rsTiles[z][x][y];
                if (tile == null) {
                    continue;
                }
                Tile tempTile = new Tile();
                tempTile.reference = tile;
                tiles.add(tempTile);
            }
        }

        return tiles;
    }
}
