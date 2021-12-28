package dev.hmh.services.oreo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class OreoService extends JobService {
    public static final String TAG = "MyTag";
    private boolean isJobCancelled = false;
    private boolean mSuccess = false;

    public OreoService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: called");
        Log.d(TAG, "onStartJob: Thread name: " + Thread.currentThread().getName());

        new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                Log.d(TAG, "run: Download started");
                while (i < 50) {
                    if (isJobCancelled)
                        return;

                    Log.d(TAG, "run: Download Progress: " + (i + 1));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                Log.d(TAG, "run: Download Completed");
                mSuccess = true;
                jobFinished(params, mSuccess);
            }
        }).start();


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        isJobCancelled = true;
        return true;
    }

}
