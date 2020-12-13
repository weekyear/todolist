package com.weekyear.todolist.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.room.TodoRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public String newTodoTitle = "";

    public MainViewModel(@NonNull Application application) {
        super(application);
        getAll();
    }

    public LiveData<List<Todo>> getAll(){
        return TodoRepository.getRepo(null).getAll();
    }

    public void saveNewTodo() {
        newTodoTitle = newTodoTitle.trim();
        if (newTodoTitle != "") {
            TodoRepository.getRepo(null).save(new Todo(newTodoTitle, newTodoTitle));
        }
    }
}
