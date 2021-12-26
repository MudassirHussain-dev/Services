package dev.hmh.services.bound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import dev.hmh.services.R;
import dev.hmh.services.start.MyDownloadService;

public class BoundServiceActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "message_key";
    private ScrollView mScroll;
    private TextView mLog;
    private ProgressBar mProgressBar;
    private MusicPlayerService mMusicPlayerService;
    private boolean mBound = false;
    private Button mPlayButton;
    private ServiceConnection mServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {

            MusicPlayerService.MyServiceBinder myServiceBinder = (MusicPlayerService.MyServiceBinder) iBinder;
            mMusicPlayerService = myServiceBinder.getService();
            mBound = true;
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            String songName = intent.getStringExtra(MESSAGE_KEY);
            String result = intent.getStringExtra(MESSAGE_KEY);
            if (result == "done") {
                mPlayButton.setText("Play");
            }
//            log(songName + " Downloaded...");

            Log.d(TAG, "onReceive: Thread name: " + Thread.currentThread().getName());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);
        initViews();
    }

    public void onBtnMusicClicked(View view) {
        if (mBound) {
            if (mMusicPlayerService.isPlaying()) {
                mMusicPlayerService.pause();
                mPlayButton.setText("Play");
            } else {
                Intent intent = new Intent(getApplicationContext(),MusicPlayerService.class);
                startService(intent);

                mMusicPlayerService.play();
                mPlayButton.setText("Pause");
            }
        }
    }

    public void runCode(View v) {
        log("Playing Music Buddy");
        displayProgressBar(true);

        //send intent to download service

//        for (String song:Playlist.songs){
//            Intent intent=new Intent(MainActivity.this,MyIntentService.class);
//            intent.putExtra(MESSAGE_KEY,song);
//
//            startService(intent);
//        }
    }

    public void clearOutput(View v) {

        Intent intent = new Intent(BoundServiceActivity.this, MyDownloadService.class);
        stopService(intent);

        mLog.setText("");
        scrollTextToEnd();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(BoundServiceActivity.this, MusicPlayerService.class);
        bindService(intent, mServiceCon, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(MusicPlayerService.MUSIC_COMPLETE));

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBound) {
            unbindService(mServiceCon);
            mBound = false;
        }
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver );
    }

    public void log(String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScroll.post(new Runnable() {
            @Override
            public void run() {
                mScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void displayProgressBar(boolean display) {
        if (display) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void initViews() {
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mPlayButton = (Button) findViewById(R.id.btnPlayMusic);
    }


}