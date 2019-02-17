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

public class MainActivity extends AppCompatActivity {

    @Override
    @TargetApi(Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button playButton = (Button) findViewById(R.id.playButton);
        Button pauseButton = (Button) findViewById(R.id.pauseButton);

        final Intent intent = new Intent(this, MusicService.class);

        // Play music
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startForegroundService(new Intent(getApplicationContext(), MusicService.class));
                //startService(new Intent(getApplicationContext(), MusicService.class));
                Log.i("test", "test");
            }
        });

        // Pause music
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startForegroundService(new Intent(getApplicationContext(), MusicService.class));
                stopService(new Intent(getApplicationContext(), MusicService.class));
                Log.i("test", "test");
            }
        });
    }
}
