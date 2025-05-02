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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scheduler.DatabaseHelper;
import com.scheduler.R;
import com.scheduler.adapters.TaskAdapter;
import com.scheduler.dialogs.AddTaskDialog;
import com.scheduler.models.Task;

public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton addTaskButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dbHelper = new DatabaseHelper(requireContext());
        setupRecyclerView(view);
        setupAddTaskButton(view);
        loadTasks();
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.tasksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
    }

    private void setupAddTaskButton(View view) {
        addTaskButton = view.findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(v -> {
            AddTaskDialog dialog = new AddTaskDialog(task -> {
                dbHelper.insertTask(task);
                loadTasks();
            });
            dialog.show(getChildFragmentManager(), "AddTaskDialog");
        });
    }

    private void loadTasks() {
        taskAdapter.submitList(dbHelper.getFutureTasks());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTasks();
    }
} 