package com.weekyear.todolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.weekyear.todolist.models.Todo;

import java.util.List;

@Dao
public interface TodoDAO extends BaseDAO<Todo> {
    @Query("SELECT * FROM todos")
    LiveData<List<Todo>> getAll();

    @Query("DELETE FROM todos")
    void deleteAll();
}
