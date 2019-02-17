package com.example.musicplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Intent intent;

    @Override
    @TargetApi(Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(), MusicService.class);

        Button playButton = (Button) findViewById(R.id.playButton);
        Button stopButton = (Button) findViewById(R.id.stopButton);

        // Play music
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startForegroundService(intent);
                //startService(new Intent(getApplicationContext(), MusicService.class));
                Log.i("LOG", "Music Started");
            }
        });

        // Stop music
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopService(intent);
                Log.i("LOG", "Music Stopped");
            }
        });
    }

}
