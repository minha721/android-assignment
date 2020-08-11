package com.example.backgroundchange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int idx = 0;
    private String[] dColor = {"#A79C8E", "#C6E5D9", "#EB9F9F"};
    LinearLayout layout;
    ProgressBar progress;
    TextView percent;
    Button chgColor, downColor;

    List<String> color = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color.add("#F8ECC9");
        color.add("#C6A49A");

        layout = (LinearLayout)findViewById(R.id.layout);
        progress = (ProgressBar)findViewById(R.id.progress);
        percent = (TextView)findViewById(R.id.percent);
        chgColor = (Button)findViewById(R.id.chgColor);
        downColor = (Button)findViewById(R.id.downColor);

        layout.setBackgroundColor(Color.parseColor(color.get(idx)));

        chgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downColor.setVisibility(View.VISIBLE);

                if(idx < color.size() - 1) {
                    layout.setBackgroundColor(Color.parseColor(color.get(++idx)));
                } else {
                    idx = 0;
                    layout.setBackgroundColor(Color.parseColor(color.get(idx)));
                }
            }
        });

        downColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);
                percent.setVisibility(View.VISIBLE);
                downColor.setVisibility(View.INVISIBLE);

                new Thread() {
                    public void run() {
                        downloadColor(new DownloadCallback() {
                            @Override
                            public void onSuccess() {
                                if(color.size() == 2)
                                    color.add(dColor[0]);
                                else if(color.size() == 3)
                                    color.add(dColor[1]);
                                else if(color.size() == 4)
                                    color.add(dColor[2]);
                            }
                        });
                    }
                }.start();
            }
        });
    }

    private void downloadColor(DownloadCallback callback) {

        for (int i = progress.getProgress(); i < 100; i = i + 1) {
            runOnUiThread(new Runnable() {
                public void run() {
                    progress.setProgress(progress.getProgress() + 1);
                    percent.setText(progress.getProgress() + "%");
                }
            });
            SystemClock.sleep(30);

            if(progress.getProgress()==100) {
                progress.setVisibility(View.INVISIBLE);
                percent.setVisibility(View.INVISIBLE);
                progress.setProgress(0);
            }
        }
        callback.onSuccess();
    }

    interface DownloadCallback {
        void onSuccess();
    }
}
