package com.weekyear.todolist.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.weekyear.todolist.models.Todo;

import java.util.List;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.ItemViewHolder> {
    public TodoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_checked, parent, false);
        return new TodoAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ItemViewHolder holder, int position) {
        Todo todo = getItem(position);
        holder.title.setText(todo.title);
    }

    class ItemViewHolder  extends RecyclerView.ViewHolder {
        public TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
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