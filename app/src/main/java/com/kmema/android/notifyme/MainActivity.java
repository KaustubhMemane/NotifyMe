package com.kmema.android.notifyme;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.notify);
        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendnotification();
            }
        });

    }

    public void sendnotification() {
        String CHANNEL_ID = "my_channel_01";
        String CHANNEL_NAME = "my Channel Name";


        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            mNotifyManager.createNotificationChannel(notificationChannel);
        }

        Notification myNotification = new NotificationCompat.Builder(MainActivity.this)
                .setContentTitle("You have been notify")
                .setContentText("This is your Notifiaction Text")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setChannel(CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(notificationPendingIntent)
                .build();

        mNotifyManager.notify(NOTIFICATION_ID, myNotification);
        Toast.makeText(this, "accepted", Toast.LENGTH_SHORT).show();
    }


}
