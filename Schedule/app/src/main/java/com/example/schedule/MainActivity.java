package com.example.schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    TextView schedule, text;
    LinearLayout linear;
    EditText inputSchedule;
    Button btnApply, btnChange;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = (CalendarView) findViewById(R.id.calendar);
        schedule = (TextView) findViewById(R.id.schedule);
        text = (TextView) findViewById(R.id.text);
        linear = (LinearLayout) findViewById(R.id.linear);
        inputSchedule = (EditText) findViewById(R.id.inputSchedule);
        btnApply = (Button) findViewById(R.id.btnApply);
        btnChange = (Button) findViewById(R.id.btnChange);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth) + "_" + Integer.toString(cDay) + ".txt";
        String str = readDiary(fileName);
        schedule.setText(str);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChange.setVisibility(View.GONE);
                linear.setVisibility(View.VISIBLE);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear + 1) + "_" + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);
                schedule.setText(str);

                linear.setVisibility(View.INVISIBLE);
                btnChange.setVisibility(View.VISIBLE);
                inputSchedule.setText("");
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linear.setVisibility(View.INVISIBLE);
                btnChange.setVisibility(View.VISIBLE);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = inputSchedule.getText().toString();
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    outFs.write(str.getBytes());
                    outFs.close();
                } catch (IOException e){
                }

                schedule.setText(str);
            }
        });
    }
    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
        } catch (IOException e) {
            schedule.setHint("일정이 없습니다.");
        }
        return diaryStr;
    }
}

//처음에 일정이 안보임