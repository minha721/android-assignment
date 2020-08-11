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

public class JoinActivity extends AppCompatActivity {
    LinearLayout joinLinear;
    ImageView joinLogo;
    TextView joinSignup;
    EditText joinEmail, joinPwd;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinLinear = (LinearLayout) findViewById(R.id.joinLinear);
        joinLogo = (ImageView) findViewById(R.id.joinLogo);
        joinSignup = (TextView) findViewById(R.id.joinSignup);
        joinEmail = (EditText) findViewById(R.id.joinEmail);
        joinPwd = (EditText) findViewById(R.id.joinPwd);
        joinBtn = (Button) findViewById(R.id.joinBtn);

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("joinEmail", joinEmail.getText().toString());
                intent.putExtra("joinPwd", joinPwd.getText().toString());
                startActivity(intent);
            }
        });
    }
}
