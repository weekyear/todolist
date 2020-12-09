package com.weekyear.todolist.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class DbClient {
    private Context mCtx;
    private static DbClient mInstance;

    //our app database object
    private AppDatabase myDatabase;

    private DbClient(Context mCtx) {
        this.mCtx = mCtx;
        //creating the app database with Room database builder
        //MyToDos is the name of the database
        myDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "AppDatabase").build();
    }

    public static synchronized DbClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DbClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return myDatabase;
    }
}
