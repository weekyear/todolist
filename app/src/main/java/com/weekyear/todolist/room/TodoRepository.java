package com.weekyear.todolist.room;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.weekyear.todolist.models.Todo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    public TodoDAO todoDAO;
    private ExecutorService executorService;

    private TodoRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());
        todoDAO = db.todoDAO();

        executorService = Executors.newSingleThreadExecutor();
    }

    private static final Object sLock = new Object();
    private static TodoRepository Instance;

    public static TodoRepository getRepo(Context context) {
        synchronized (sLock) {
            if(Instance==null) {
                Instance= new TodoRepository(context);
            }
            return Instance;
        }
    }

    public LiveData<List<Todo>> getAll(){
        return todoDAO.getAll();
    }

    public void save(Todo todo){
        executorService.execute(() -> todoDAO.insert(todo));
    }

    public void delete(){
        executorService.execute(() -> todoDAO.deleteAll());
    }
}
