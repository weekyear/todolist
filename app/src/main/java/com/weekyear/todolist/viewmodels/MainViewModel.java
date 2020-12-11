package com.weekyear.todolist.viewmodels;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.room.AppDatabase;
import com.weekyear.todolist.room.TodoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {
    public TodoDAO todoDAO;
    private ExecutorService executorService;
    public LiveData<List<Todo>> AllTodos;

    public String newTodoTitle = "To-Do List";

    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = AppDatabase.getInstance(getApplication().getApplicationContext());
        todoDAO = db.todoDAO();

        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Todo>> getAll(){
        return todoDAO.getAll();
    }

    public void save(Todo todo){
        executorService.execute(() -> todoDAO.insert(todo));
    }

    public void saveNewTodo() {
        save(new Todo(newTodoTitle, newTodoTitle));
    }

    public void delete(){
        executorService.execute(() -> todoDAO.deleteAll());
    }
}
