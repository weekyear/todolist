package com.weekyear.todolist.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.weekyear.todolist.R;
import com.weekyear.todolist.models.Todo;
import com.weekyear.todolist.room.TodoRepository;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.ViewHolder> {
    public TodoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setOnCheckedChangeListener(null);
        Todo todo = getItem(position);
        holder.todo = todo;
        holder.title.setText(todo.title);
        holder.checkBox.setChecked(todo.isCompleted);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            todo.isCompleted = isChecked;
            TodoRepository.getRepo(buttonView.getContext()).save(todo);
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Todo todo;
        public TextView title;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            checkBox = itemView.findViewById(R.id.completeCheckBox);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    TodoRepository.getRepo(itemView.getContext()).delete(todo);
                }
            });
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