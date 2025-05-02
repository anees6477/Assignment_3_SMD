package com.scheduler.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.scheduler.R;
import com.scheduler.models.Task;

import java.util.Calendar;

public class AddTaskDialog extends DialogFragment {
    private TextInputLayout titleInput;
    private TextInputLayout descriptionInput;
    private Button dateTimeButton;
    private Button saveButton;
    private Calendar selectedDateTime;
    private final OnTaskAddedListener onTaskAddedListener;

    public interface OnTaskAddedListener {
        void onTaskAdded(Task task);
    }

    public AddTaskDialog(OnTaskAddedListener listener) {
        this.onTaskAddedListener = listener;
        this.selectedDateTime = Calendar.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleInput = view.findViewById(R.id.titleInput);
        descriptionInput = view.findViewById(R.id.descriptionInput);
        dateTimeButton = view.findViewById(R.id.dateTimeButton);
        saveButton = view.findViewById(R.id.saveButton);

        setupDateTimeButton();
        setupSaveButton();
    }

    private void setupDateTimeButton() {
        dateTimeButton.setOnClickListener(v -> showDatePicker());
        updateDateTimeButtonText();
    }

    private void showDatePicker() {
        new DatePickerDialog(
            requireContext(),
            (view, year, month, day) -> {
                selectedDateTime.set(Calendar.YEAR, year);
                selectedDateTime.set(Calendar.MONTH, month);
                selectedDateTime.set(Calendar.DAY_OF_MONTH, day);
                showTimePicker();
            },
            selectedDateTime.get(Calendar.YEAR),
            selectedDateTime.get(Calendar.MONTH),
            selectedDateTime.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void showTimePicker() {
        new TimePickerDialog(
            requireContext(),
            (view, hour, minute) -> {
                selectedDateTime.set(Calendar.HOUR_OF_DAY, hour);
                selectedDateTime.set(Calendar.MINUTE, minute);
                updateDateTimeButtonText();
            },
            selectedDateTime.get(Calendar.HOUR_OF_DAY),
            selectedDateTime.get(Calendar.MINUTE),
            true
        ).show();
    }

    private void updateDateTimeButtonText() {
        dateTimeButton.setText(String.format(
            "%02d/%02d/%d %02d:%02d",
            selectedDateTime.get(Calendar.DAY_OF_MONTH),
            selectedDateTime.get(Calendar.MONTH) + 1,
            selectedDateTime.get(Calendar.YEAR),
            selectedDateTime.get(Calendar.HOUR_OF_DAY),
            selectedDateTime.get(Calendar.MINUTE)
        ));
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            String title = titleInput.getEditText() != null ? 
                titleInput.getEditText().getText().toString() : "";
            String description = descriptionInput.getEditText() != null ? 
                descriptionInput.getEditText().getText().toString() : "";

            if (title.trim().isEmpty()) {
                titleInput.setError("Title is required");
                return;
            }

            Task task = new Task(
                title,
                description,
                String.valueOf(selectedDateTime.getTimeInMillis()),
                "pending"
            );

            onTaskAddedListener.onTaskAdded(task);
            dismiss();
        });
    }
} 