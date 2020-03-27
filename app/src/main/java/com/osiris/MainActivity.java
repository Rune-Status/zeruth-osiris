package com.osiris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jagex.oldscape.android.AndroidLauncher;
import com.osiris.api.Client;
import com.osiris.api.Player;
import com.osiris.api.Statics;

/*
    This class handles loading OSRS as well as initializing any of our added features.

    Keep all mobile required classes in the com.osiris package, as the patcher will only
    move that package to osrs mobile.
 */
public class MainActivity extends Activity {

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

        Log.d(this.getClass().getName(), "Starting debug thread (5000ms tick)");
        Thread debug = new Thread(() -> {
            while (true) {
                try {
                    String username;
                    Thread.sleep(5000);

                    Statics statics = new Statics();
                    Player localPlayer = statics.getLocalPlayer();
                    if (localPlayer.reference == null) {
                        username = "LocalPlayer Null";
                    } else {
                        if (localPlayer.getUsername().reference == null) {
                            username = "Username Null";
                        } else {
                            username = localPlayer.getUsername().getName1();
                        }
                    }

                    Log.e(this.getClass().getName(), "LocalPlayer: " + username);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        debug.start();

        Toast.makeText(this,
                "Welcome to OpenOSRS Mobile!", Toast.LENGTH_LONG).show();
    }
}