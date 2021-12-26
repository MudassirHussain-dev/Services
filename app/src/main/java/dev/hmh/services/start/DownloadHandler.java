package dev.hmh.services.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import dev.hmh.services.MainActivity;
import dev.hmh.services.start.MyDownloadService;


public class DownloadHandler extends Handler {

    private static final String TAG = "MyTag";
    public static final String SERVICE_MESSAGE = "ServiceMessage";
    private MyDownloadService mService;
    private Context mContext;


    public DownloadHandler() {
    }

    @Override
    public void handleMessage(Message msg) {

        downloadSong(msg.obj.toString());
        mService.stopSelf(msg.arg1);
        Log.d(TAG, "handleMessage: Song Downloaded: " + msg.obj.toString() + " Intent Id: " + msg.arg1);

        sendMessageToUi(msg.obj.toString());

    }

    private void sendMessageToUi(String s) {
        Intent intent = new Intent(SERVICE_MESSAGE);
        intent.putExtra(StartServiceActivity.MESSAGE_KEY, s);

        //local broad cast receiver

        LocalBroadcastManager.getInstance(mContext)
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

    public void setService(MyDownloadService downloadService) {
        this.mService = downloadService;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

}