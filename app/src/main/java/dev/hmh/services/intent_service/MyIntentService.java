package dev.hmh.services.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyTag";
    public static final String INTENT_SERVICE_MESSAGE = "IntentServiceMessage";

    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyIntentService");
        Log.d(TAG, "onCreate: Thread name: " + Thread.currentThread().getName());

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: MyIntentService");
        Log.d(TAG, "onHandleIntent: Thread name: " + Thread.currentThread().getName());

        String songName = intent.getStringExtra(IntentServiceActivity.MESSAGE_KEY);
        downloadSong(songName);
        sendMessageToUI(songName);
    }

    private void sendMessageToUI(String songName) {
        Intent intent = new Intent(INTENT_SERVICE_MESSAGE);
        intent.putExtra(IntentServiceActivity.MESSAGE_KEY, songName);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(intent);
    }

    private void downloadSong(final String songName) {
        Log.d(TAG, "run: staring download");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "downloadSong: " + songName + " Downloaded...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: MyIntentService");
        Log.d(TAG, "onDestroy: Thread name: " + Thread.currentThread().getName());

    }
}
