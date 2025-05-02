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
import com.scheduler.models.Notification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationAdapter extends ListAdapter<Notification, NotificationAdapter.NotificationViewHolder> {

    public NotificationAdapter() {
        super(new NotificationDiffCallback());
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private final TextView messageTextView;
        private final TextView timeTextView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.notificationMessage);
            timeTextView = itemView.findViewById(R.id.notificationTime);
        }

        public void bind(Notification notification) {
            messageTextView.setText(notification.getMessage());
            timeTextView.setText(formatDateTime(notification.getDatetime()));
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

    static class NotificationDiffCallback extends DiffUtil.ItemCallback<Notification> {
        @Override
        public boolean areItemsTheSame(@NonNull Notification oldItem, @NonNull Notification newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notification oldItem, @NonNull Notification newItem) {
            return oldItem.equals(newItem);
        }
    }
} 