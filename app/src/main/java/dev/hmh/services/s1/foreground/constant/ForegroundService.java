package dev.hmh.services.s1.foreground.constant;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import dev.hmh.services.R;


public class ForegroundService extends Service {

    public static final String MY_TAG = "MyTag";

    public ForegroundService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showNotification();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                Log.d(MY_TAG, "run: starting downlaod");

                int i=0;

                while (i<=10){

                    Log.d(MY_TAG, "run: Progress is: "+(i+1));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                Log.d(MY_TAG, "run: download completed");

                stopForeground(true);
                stopSelf();

            }
        });

        thread.start();

        return  START_STICKY;

    }

    private void showNotification() {

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"channelId");

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("This is service notificaion")
                .setContentTitle("Title");
        Notification notification=builder.build();

        startForeground(123,notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MY_TAG, "onDestroy: called");
    }
}