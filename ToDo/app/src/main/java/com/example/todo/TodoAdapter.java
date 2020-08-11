package com.example.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<Todo> todoList;
    private ListDBHelper dbHelper;

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        TodoViewHolder viewHolder = new TodoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.txtList.setText(todoList.get(position).getList());
    }

    @Override
    public int getItemCount() {
        return (null != todoList ? todoList.size() : 0);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtList;
        protected ImageButton btnDelete;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            dbHelper = ListDBHelper.getInstance(itemView.getContext());

            this.txtList = (TextView)itemView.findViewById(R.id.txtList);
            this.btnDelete = (ImageButton)itemView.findViewById(R.id.btnDelete);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        todoList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        String tmp = todoList.get(position).getList();
                        dbHelper.delete(tmp);
                        todoList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public TodoAdapter(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }
}
