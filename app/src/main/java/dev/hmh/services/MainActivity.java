package dev.hmh.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import dev.hmh.services.bound.BoundServiceActivity;
import dev.hmh.services.foreground.ForegroundActivity;
import dev.hmh.services.intent_service.IntentServiceActivity;
import dev.hmh.services.start.MyDownloadService;
import dev.hmh.services.start.StartServiceActivity;


public class MainActivity extends AppCompatActivity {
    Button btnStartService, btnBoundService, btnIntentService, btnForegroundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        btnBoundService = findViewById(R.id.btnBoundService);
        btnIntentService = findViewById(R.id.btnIntentService);
        btnForegroundService = findViewById(R.id.btnForegroundService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StartServiceActivity.class));
            }
        });
        btnBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BoundServiceActivity.class));
            }
        });

        btnIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentServiceActivity.class));
            }
        });
        btnForegroundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForegroundActivity.class));
            }
        });

    }
}