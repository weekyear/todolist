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

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll(Todo... todos);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(Todo todo);

    @Query("DELETE FROM todos")
    void deleteAll();
}
