package com.example.musicplayer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.support.v4.app.NotificationCompat.PRIORITY_MIN;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {

        mediaPlayer = MediaPlayer.create(this, R.raw.bensound_littleidea);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String foregroundId;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            foregroundId = "foregroundID";
            String foreGroundName = "Music Player Service";
            NotificationChannel channel = new NotificationChannel(foregroundId, foreGroundName, NotificationManager.IMPORTANCE_HIGH);

            channel.setImportance(NotificationManager.IMPORTANCE_NONE);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(channel);
        }
        else{
            foregroundId = "Bad version";
        }
        NotificationCompat.Builder notifactionCompat = new NotificationCompat.Builder(this, foregroundId);
        Notification notification = notifactionCompat.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(101, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        Log.i("LOG", "Service stopped");
    }
}
