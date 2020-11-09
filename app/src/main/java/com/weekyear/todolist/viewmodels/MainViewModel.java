package com.weekyear.todolist.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.weekyear.todolist.models.Todo;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Todo>> todos;
    public LiveData<List<Todo>> getTodos(){
        if (todos == null){
            todos = new MutableLiveData<List<Todo>>();
            loadTodos();
        }
        return todos;
    }

    private void loadTodos() {
        // Do an asynchronous operation to fetch todos.
        // TodoRepository 끌고 와서 todos에 데이터에 박혀있는 todos를 박아 넣어야 할듯
    }
}
