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

public class MainViewModel extends AndroidViewModel {
    TodoDAO todoDAO;

    private MutableLiveData<List<Todo>> todos;

    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = AppDatabase.getInstance(getApplication().getApplicationContext());

        todoDAO = db.todoDAO();
        todos = new MutableLiveData<List<Todo>>(todoDAO.getAll().getValue());
//        List<Todo> todos = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Todo todo = new Todo(i, String.format("%d번째 Todo", i), String.format("%d번째 내용", i));
//            todos.add(todo);
//        }

//        new InsertThread(todos).start();
    }

    public LiveData<List<Todo>> getTodos(){
        if (todos == null){
            todos = new MutableLiveData<List<Todo>>();
//            new GetAllThread(todos).start();
        }
        return todos;
    }

    class InsertThread extends Thread {
        List<Todo> todos;
        public InsertThread(List<Todo> todos){
            this.todos = todos;
        }

        @Override
        public void run() {
            todoDAO.insertAll(todos.toArray(new Todo[todos.size()]));
        }
    }

    class GetAllThread extends Thread {
        MutableLiveData<List<Todo>> liveData;
        public GetAllThread(MutableLiveData<List<Todo>> liveData) {
            this.liveData = liveData;
        }
        @Override
        public void run() {
//            List<Todo> results = todoDAO.getAll();
//            liveData.postValue(results);
//            for (Todo todo: results) {
//                Log.d("weekyear", todo.getId() + "," + todo.getTitle() + "," + todo.getContent());
//            }
        }
    }
}
