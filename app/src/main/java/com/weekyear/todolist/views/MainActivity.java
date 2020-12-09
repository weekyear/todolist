package com.weekyear.todolist.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weekyear.todolist.R;
import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
        MainViewModel viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
//        viewModel.getTodos().observe(this, todos -> {
//
//        });
        RecyclerView recyclerView=findViewById(R.id.todoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TodoAdapter(viewModel.getTodos().getValue()));
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
        private List<Todo> todos = new ArrayList<>();
        public TodoAdapter(List<Todo> todos) {
            this.todos = todos;
//            for (int i = 0; i < 10; i++) {
//                Todo todo = new Todo(String.format("%d번째 Todo", i), String.format("%d번째 내용", i));
//                this.todos.add(todo);
//            }
        }

        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_checked, parent, false);
            return new TodoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            Todo todo = todos.get(position);
            holder.title.setText(todo.title);
        }

        @Override
        public int getItemCount() {
            return todos.size();
        }

    }

    private class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
        }
    }
}