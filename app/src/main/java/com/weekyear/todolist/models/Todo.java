package com.weekyear.todolist.models;

import android.os.Build;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;
import java.util.Date;

@Entity(tableName = "todos")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String title;
    public String content;
    public Date date;
    public Boolean isCompleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Todo(String title, String content) {
        this.title = title;
        this.content = content;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = new Date();
        }
        this.isCompleted = false;
    }
}