package dev.hmh.services.s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.hmh.services.R;
import dev.hmh.services.s1.bound.BoundServiceActivity;
import dev.hmh.services.s1.foreground.ForegroundActivity;
import dev.hmh.services.s1.intent_service.IntentServiceActivity;
import dev.hmh.services.s1.start.StartServiceActivity;

public class S1Activity extends AppCompatActivity {

    Button btnStartService, btnBoundService, btnIntentService, btnForegroundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s1);


        btnStartService = findViewById(R.id.btnS1);
        btnBoundService = findViewById(R.id.btnS2);
        btnIntentService = findViewById(R.id.btnIntentService);
        btnForegroundService = findViewById(R.id.btnForegroundService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S1Activity.this, StartServiceActivity.class));
            }
        });
        btnBoundService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S1Activity.this, BoundServiceActivity.class));
            }
        });

        btnIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(S1Activity.this, IntentServiceActivity.class));
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