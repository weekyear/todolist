package com.weekyear.todolist.helper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.weekyear.todolist.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class ListLiveData<T> extends LiveData<List<T>> {
    private List<T> temp = new ArrayList<T>();

    public ListLiveData() {
        this.setValue(temp);
    }

    public void add(T item) {
        temp.add(item);
        this.setValue(temp);
    }

    public void addAll(List<T> items) {
        temp.addAll(items);
        this.setValue(temp);
    }

    public void remove(T item) {
        temp.remove(item);
        this.setValue(temp);
    }

    public void clear() {
        temp.clear();
        this.setValue(temp);
    }
}
