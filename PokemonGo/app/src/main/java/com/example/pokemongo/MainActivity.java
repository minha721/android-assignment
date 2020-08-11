package com.example.pokemongo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainFirstLinear, mainSecondLinear;
    ImageView mainLogo;
    EditText mainEmail, mainPwd;
    Button mainBtn;
    TextView mainText, mainToJoin;

    String Email;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFirstLinear = (LinearLayout) findViewById(R.id.mainFirstLinear);
        mainSecondLinear = (LinearLayout) findViewById(R.id.mainSecondLinear);
        mainLogo = (ImageView) findViewById(R.id.mainLogo);
        mainEmail = (EditText) findViewById(R.id.mainEmail);
        mainPwd = (EditText) findViewById(R.id.mainPwd);
        mainBtn = (Button) findViewById(R.id.mainBtn);
        mainText = (TextView) findViewById(R.id.mainText);
        mainToJoin = (TextView) findViewById(R.id.mainToJoin);

        Intent intent = getIntent();

        Email=intent.getStringExtra("joinEmail");
        Password=intent.getStringExtra("joinPwd");

        mainEmail.setText(Email);
        mainPwd.setText(Password);

        mainToJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

    }
}
