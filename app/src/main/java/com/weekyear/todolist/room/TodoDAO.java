package com.weekyear.todolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.weekyear.todolist.models.Todo;

import java.util.List;

@Dao
public interface TodoDAO {
    @Query("SELECT * FROM todos")
    LiveData<List<Todo>> getAll();

    @Insert
    void insertAll(Todo... todos);

    @Query("DELETE FROM todos")
    void deleteAll();
}
