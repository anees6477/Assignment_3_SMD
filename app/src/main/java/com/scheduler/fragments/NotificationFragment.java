package com.scheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.DatabaseHelper;
import com.scheduler.R;
import com.scheduler.adapters.NotificationAdapter;
import com.scheduler.models.Notification;

import java.util.Arrays;
import java.util.List;

public class NotificationFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dbHelper = new DatabaseHelper(requireContext());
        setupRecyclerView(view);
        loadNotifications();
        
        // Add some dummy notifications if none exist
        if (dbHelper.getNotifications().isEmpty()) {
            addDummyNotifications();
        }
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.notificationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter();
        recyclerView.setAdapter(notificationAdapter);
    }

    private void loadNotifications() {
        notificationAdapter.submitList(dbHelper.getNotifications());
    }

    private void addDummyNotifications() {
        List<Notification> dummyNotifications = Arrays.asList(
            new Notification(
                "Welcome to Task Scheduler!",
                String.valueOf(System.currentTimeMillis())
            ),
            new Notification(
                "You can manage your tasks here",
                String.valueOf(System.currentTimeMillis() - 3600000)
            ),
            new Notification(
                "Don't forget to check your upcoming tasks",
                String.valueOf(System.currentTimeMillis() - 7200000)
            )
        );

        for (Notification notification : dummyNotifications) {
            dbHelper.insertNotification(notification);
        }
        loadNotifications();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNotifications();
    }
} 