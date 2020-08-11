package com.example.pikachu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    TextView text;
    ImageView pikachu;
    View toastView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMonsterBall:
                text.setText("몬스터볼");
                baseLayout.setBackgroundColor(Color.rgb(110,240,255));
                return true;
            case R.id.itemGym:
                text.setText("체육관");
                baseLayout.setBackgroundColor(Color.rgb(255,50,50));
                return true;
            case R.id.itemPark:
                text.setText("공원");
                baseLayout.setBackgroundColor(Color.rgb(155,240,72));
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        if(v == pikachu) {
            menu.setHeaderTitle("피카츄에게 무엇을 할까?");
            mInflater.inflate(R.menu.menu2, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fruit:
                final String[] fruitArray = new String[] {"라즈열매", "나나열매", "파인열매"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("어떤 열매를 먹일까?");
                dlg.setItems(fruitArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast1 = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toast1.setView(toastView);
                        toast1.setGravity(Gravity.CENTER, 0, -100);
                        toast1.show();
                    }
                });
                dlg.setPositiveButton("취소", null);
                dlg.show();
                return true;
            case R.id.sleep:
                Toast toast2 = Toast.makeText(MainActivity.this, "잘잤다!", Toast.LENGTH_SHORT);
                toast2.show();
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        text = (TextView) findViewById(R.id.text);
        pikachu = (ImageView) findViewById(R.id.pikachu);

        registerForContextMenu(pikachu);
    }
}
