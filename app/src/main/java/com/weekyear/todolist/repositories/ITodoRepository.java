package com.weekyear.todolist.repositories;

import com.weekyear.todolist.models.Todo;

public interface ITodoRepository {
    int saveTodo();
    Todo getTodo(int id);

}
