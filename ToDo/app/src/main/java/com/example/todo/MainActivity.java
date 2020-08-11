package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListDBHelper dbHelper;
    private ArrayList<Todo> todoArrayList;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = ListDBHelper.getInstance(getApplicationContext());

        RecyclerView recycler = (RecyclerView)findViewById(R.id.recycler);
        Button btn = (Button)findViewById(R.id.btn);
        final EditText todoEdit = (EditText)findViewById(R.id.todoEdit);
        ImageButton btnDelete = (ImageButton)findViewById(R.id.btnDelete);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        todoArrayList = dbHelper.getAll();
        todoAdapter = new TodoAdapter(todoArrayList);
        recycler.setAdapter(todoAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String list = todoEdit.getText().toString();
                Todo data = new Todo(list);
                todoArrayList.add(data);
                dbHelper.insert(data);
                todoAdapter.notifyDataSetChanged();

                todoEdit.clearFocus();
                todoEdit.setText(null);
            }
        });
    }
}
