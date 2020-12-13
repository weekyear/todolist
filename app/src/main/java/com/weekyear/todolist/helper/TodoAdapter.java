package com.weekyear.todolist.helper;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.weekyear.todolist.R;
import com.weekyear.todolist.databinding.TodoItemBinding;
import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.room.AppDatabase;
import com.weekyear.todolist.room.TodoRepository;

import java.util.List;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.ItemViewHolder> {
    public TodoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new TodoAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ItemViewHolder holder, int position) {
        holder.checkBox.setOnCheckedChangeListener(null);
        Todo todo = getItem(position);
        holder.title.setText(todo.title);
        holder.checkBox.setChecked(todo.isCompleted);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.isCompleted = isChecked;
            TodoRepository.getRepo(buttonView.getContext()).save(todo);
        });
    }

    class ItemViewHolder  extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckBox checkBox;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            checkBox = itemView.findViewById(R.id.completeCheckBox);
        }
    }

    public static DiffUtil.ItemCallback<Todo> DIFF_CALLBACK = new  DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.equals(newItem);
        }
    };
}