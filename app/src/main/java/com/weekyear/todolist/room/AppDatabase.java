package com.weekyear.todolist.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.weekyear.todolist.helper.Converters;
import com.weekyear.todolist.models.Todo;

@Database(entities = {Todo.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDAO todoDAO();

    private static AppDatabase Instance;

    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if(Instance==null) {
                Instance= Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "todo-db")
                        .build();
            }
            return Instance;
        }
    }
}
