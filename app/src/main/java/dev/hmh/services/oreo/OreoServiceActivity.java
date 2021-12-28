package dev.hmh.services.oreo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.hmh.services.R;

public class OreoServiceActivity extends AppCompatActivity {
    public static final int JOB_ID = 1001;
    public static final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreo_service);
    }

    public void scheduleService(View view) {
        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName= new ComponentName(this,OreoService.class);
        JobInfo jobInfo=new JobInfo.Builder(JOB_ID,componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setMinimumLatency(0)
                .setPersisted(true)
                .build();

        int result = jobScheduler.schedule(jobInfo);

        if(result == JobScheduler.RESULT_SUCCESS)
            Log.d(TAG, "scheduleService: Job Scheduled");
        else
            Log.d(TAG, "scheduleService: Job not scheduled");
    }

    public void cancelService(View view) {
        JobScheduler jobScheduler= (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(JOB_ID);
        Log.d(TAG, "cancelService: job cancelled");
    }
}