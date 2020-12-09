package com.weekyear.todolist.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.weekyear.todolist.R;
import com.weekyear.todolist.databinding.ActivityMainBinding;
import com.weekyear.todolist.helper.TodoAdapter;
import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.viewmodels.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TodoAdapter adapter;
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new TodoAdapter();
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
        viewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        binding.setVm(viewModel);
        binding.setView(this);

        viewModel.delete();
        Todo[] initTodos = {
                new Todo("1번 제목", "1번 내용"),
                new Todo("2번 제목", "2번 내용"),
                new Todo("3번 제목", "3번 내용"),
                new Todo("4번 제목", "4번 내용"),
                new Todo("5번 제목", "5번 내용"),
                new Todo("6번 제목", "6번 내용"),
        };
        for (int i = 0; i < initTodos.length; i++) {
            viewModel.save(initTodos[i]);
        }

        viewModel.getAll().observe(this, todos -> adapter.submitList(todos));

        RecyclerView recyclerView=findViewById(R.id.todoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        viewModel.saveNewTodo();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.todoEditText.getWindowToken(), 0);
    }
}