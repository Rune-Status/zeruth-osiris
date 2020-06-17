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
package com.osiris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jagex.oldscape.android.AndroidLauncher;
import com.osiris.api.Client;
import com.osiris.api.GameObject;
import com.osiris.api.Player;
import com.osiris.api.Statics;
import com.osiris.api.Statics1;
import com.osiris.api.Tile;

/*
    This class handles loading OSRS as well as initializing any of our added features.

    Keep all mobile required classes in the com.osiris package, as the patcher will only
    move that package to osrs mobile.
 */
public class MainActivity extends Activity {

    private boolean changedAlphas = false;

    public Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, AndroidLauncher.class);

        //OSRS has checks for being task root so we must use these flags
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Log.d(this.getClass().getName(), "Starting OSRS activity");

        startActivity(intent);

        client = new Client();

        Thread debug = new Thread(() -> {
            Log.d(this.getClass().getName(), "Starting debug thread (5000ms tick)");
            while (true) {
                try {
                    String username;
                    Thread.sleep(5000);

                    Statics statics = new Statics();

                    Client.localPlayer = statics.getLocalPlayer();
                    if (Client.localPlayer.reference == null) {
                        username = "LocalPlayer Null";
                    } else {
                        if (Client.localPlayer.getUsername().reference == null) {
                            username = "Username Null";
                        } else {
                            username = Client.localPlayer.getUsername().getName();
                        }
                    }

                    Log.e(this.getClass().getName(), "LocalPlayer: " + username);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread clickBoxes = new Thread(() -> {
            Log.d(this.getClass().getName(), "Starting clickboxes thread (50ms tick)"); // cycle is high so less likely to miss updating.
            while (true) {
                try {
                    Thread.sleep(50);
                    Statics statics = new Statics();
                    Client.localPlayer = statics.getLocalPlayer();

                    if (Client.localPlayer.reference != null)
                    {
                        Statics1 statics1 = new Statics1();
                        for (Tile t : statics1.getScene().getTiles())
                            for (GameObject go : t.getGameObjects())
                            {
                                if (go.reference != null)
                                    switch (go.getID())
                                    {
                                        // Lumby rooftop example
                                        case 14413:
                                        case 14414:
                                        case 14833:
                                        case 14834:
                                        case 14835:
                                        case 14836:
                                        case 14841:
                                            if (go.getEntity().reference !=null)
                                            {
                                                if (go.getEntity().getModel().reference != null)
                                                {
                                                    byte[] alphas = go.getEntity().getModel().getFaceAlphas();
                                                    int i = 0;
                                                    byte[] newAlphas = new byte[alphas.length];
                                                    for (byte b : alphas)
                                                    {
                                                        newAlphas[i] = 0; // 0 = opaque, -2 = transparent
                                                        i++;
                                                    }
                                                    go.getEntity().getModel().setFaceAlphas(newAlphas);
                                                }
                                            }
                                            if (go.getEntity().reference !=null)
                                            {
                                                if (go.getEntity().getModel().reference != null)
                                                {
                                                    int[] alphas = go.getEntity().getModel().getFaceColors();
                                                    int i = 0;
                                                    int[] newAlphas = new int[alphas.length];
                                                    for (int b : alphas)
                                                    {
                                                        newAlphas[i] = 86; // white
                                                        i++;
                                                    }
                                                    go.getEntity().getModel().setFaceColors(newAlphas);
                                                }
                                            }
                                            break;
                                    }
                            }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        debug.start();
        clickBoxes.start();

        Toast.makeText(this,
                "Welcome to OSiris!", Toast.LENGTH_LONG).show();
    }
}