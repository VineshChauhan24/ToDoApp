package com.example.taimoortahir.todoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Taimoor Tahir on 02-Aug-17.
 */

public class BroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Intent intent1 = new Intent(context, AlarmService.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            int interval = 8000;

            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

            Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
            //context.startService(intent1);
        }
    }
}
