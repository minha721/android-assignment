package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    Button btnStart, btnStop, btnContinue, btnRecord, btnReset;
    ScrollView scrollView;
    LinearLayout innerLayout;
    long tempTime1;
    long tempTime2;
    long newBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = (Chronometer) findViewById(R.id.chronometer);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnReset = (Button) findViewById(R.id.btnReset);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        innerLayout = (LinearLayout) findViewById(R.id.innerLayout);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStop.setVisibility(View.GONE);
                btnRecord.setVisibility(View.GONE);
                btnContinue.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                tempTime1 = SystemClock.elapsedRealtime();
                chrono.stop();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnContinue.setVisibility(View.GONE);
                btnReset.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                btnRecord.setVisibility(View.VISIBLE);
                tempTime2 = SystemClock.elapsedRealtime();
                newBase = chrono.getBase() + (tempTime2 - tempTime1);
                chrono.setBase(newBase);
                chrono.start();
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);

                TextView markText = new TextView(MainActivity.super.getBaseContext());
                markText.setLayoutParams(params);

                markText.setGravity(Gravity.CENTER);
                markText.setText(chrono.getText());
                markText.setTextSize(25);
                innerLayout.addView(markText, 0);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnContinue.setVisibility(View.GONE);
                btnReset.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);
                chrono.setBase(SystemClock.elapsedRealtime());
                innerLayout.removeAllViews();
            }
        });
    }
}
