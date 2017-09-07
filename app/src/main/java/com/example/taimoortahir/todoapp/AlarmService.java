package com.example.taimoortahir.todoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Taimoor Tahir on 02-Aug-17.
 */

public class AlarmService extends Service {

    private NotificationManager nManager;
    private NotificationCompat.Builder nBuilder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        nManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent2 = new Intent(this.getApplicationContext(), MainActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        nBuilder = new NotificationCompat.Builder(this.getApplicationContext());

//        Notification notification = new Notification(R.drawable.message, "This is a test message!", System.currentTimeMillis());

        PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = nBuilder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.message).setWhen(System.currentTimeMillis())
                .setContentTitle("my notification")
                .setContentText("this is my custom notification")
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        nManager.notify(0, notification);
    }
}
