package com.scheduler.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.R;
import com.scheduler.models.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskViewHolder> {

    public TaskAdapter() {
        super(new TaskDiffCallback());
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView descriptionTextView;
        private final TextView dateTimeTextView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.taskTitle);
            descriptionTextView = itemView.findViewById(R.id.taskDescription);
            dateTimeTextView = itemView.findViewById(R.id.taskDateTime);
        }

        public void bind(Task task) {
            titleTextView.setText(task.getTitle());
            descriptionTextView.setText(task.getDescription());
            dateTimeTextView.setText(formatDateTime(task.getDatetime()));
        }

        private String formatDateTime(String dateTime) {
            try {
                Date date = new Date(Long.parseLong(dateTime));
                return new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(date);
            } catch (Exception e) {
                return dateTime;
            }
        }
    }

    static class TaskDiffCallback extends DiffUtil.ItemCallback<Task> {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    }
} 