package dev.hmh.services.start;

import android.os.Looper;

import dev.hmh.services.start.DownloadHandler;


public class DownloadThread extends Thread {

    private static final String TAG = "MyTag";
    public DownloadHandler mHandler;

    public DownloadThread() {
    }

    @Override
    public void run() {

        Looper.prepare();
        mHandler=new DownloadHandler();
        Looper.loop();
        this.setName("MyDownloadThread");

    }
}
