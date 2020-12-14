package com.weekyear.todolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.weekyear.todolist.models.Todo;

import java.util.List;

public interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(T... dataList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T data);

    @Delete
    void delete(T data);
}
