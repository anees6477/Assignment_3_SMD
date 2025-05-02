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
import com.scheduler.adapters.TaskAdapter;

public class PastFragment extends Fragment {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_past, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        dbHelper = new DatabaseHelper(requireContext());
        setupRecyclerView(view);
        loadPastTasks();
    }

    private void setupRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.pastTasksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
    }

    private void loadPastTasks() {
        taskAdapter.submitList(dbHelper.getPastTasks());
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPastTasks();
    }
} 