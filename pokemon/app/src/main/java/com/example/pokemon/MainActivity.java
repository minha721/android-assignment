package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoChikorita, rdoCyndaquil, rdoTotodile;
    Button btnOK;
    ImageView imgOak, imgPokemon, imgAsh;

    String Pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("포켓몬 선택");

        chkAgree = (CheckBox) findViewById(R.id.ChkAgree);
        text1 = (TextView) findViewById(R.id.Text1);
        imgOak = (ImageView) findViewById(R.id.Oak);

        text2 = (TextView) findViewById(R.id.Text2);
        rGroup1 = (RadioGroup) findViewById(R.id.Rgroup1);
        rdoChikorita = (RadioButton) findViewById(R.id.Chikorita);
        rdoCyndaquil = (RadioButton) findViewById(R.id.Cyndaquil);
        rdoTotodile = (RadioButton) findViewById(R.id.Totodile);
        btnOK = (Button) findViewById(R.id.BtnOk);
        imgPokemon = (ImageView) findViewById(R.id.ImgPokemon);
        imgAsh = (ImageView) findViewById(R.id.AshKetchum);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chkAgree.isChecked()) {
                    text1.setVisibility(View.GONE);
                    imgOak.setVisibility(View.GONE);
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);
                    imgPokemon.setVisibility(View.VISIBLE);
                } else {
                    text1.setVisibility(View.VISIBLE);
                    imgOak.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.GONE);
                    rGroup1.setVisibility(View.GONE);
                    btnOK.setVisibility(View.GONE);
                    imgPokemon.setVisibility(View.GONE);
                }
            }
        });

        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                imgAsh.setVisibility(View.GONE);
                imgPokemon.setVisibility(View.VISIBLE);
                switch (rGroup1.getCheckedRadioButtonId()) {
                    case R.id.Chikorita:
                        Pokemon = "치코리타";
                        imgPokemon.setImageResource(R.drawable.chikorita);
                        break;
                    case R.id.Cyndaquil:
                        Pokemon = "브케인";
                        imgPokemon.setImageResource(R.drawable.cyndaquil);
                        break;
                    case R.id.Totodile:
                        Pokemon = "리아코";
                        imgPokemon.setImageResource(R.drawable.totodile);
                        break;
                }
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgPokemon.setVisibility(View.GONE);
                imgAsh.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), Pokemon + "! 너로 정했다!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
