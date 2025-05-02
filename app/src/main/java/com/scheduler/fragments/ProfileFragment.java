package com.scheduler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.scheduler.R;
import com.scheduler.utils.PreferencesManager;

public class ProfileFragment extends Fragment {
    private TextInputLayout nameInput;
    private TextInputLayout emailInput;
    private Switch darkModeSwitch;
    private PreferencesManager preferencesManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferencesManager = new PreferencesManager(requireContext());
        setupViews(view);
        loadUserPreferences();
        setupListeners();
    }

    private void setupViews(View view) {
        nameInput = view.findViewById(R.id.nameInput);
        emailInput = view.findViewById(R.id.emailInput);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
    }

    private void loadUserPreferences() {
        if (nameInput.getEditText() != null) {
            nameInput.getEditText().setText(preferencesManager.getUserName());
        }
        if (emailInput.getEditText() != null) {
            emailInput.getEditText().setText(preferencesManager.getUserEmail());
        }
        darkModeSwitch.setChecked(preferencesManager.isDarkMode());
    }

    private void setupListeners() {
        if (nameInput.getEditText() != null) {
            nameInput.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    preferencesManager.setUserName(nameInput.getEditText().getText().toString());
                }
            });
        }

        if (emailInput.getEditText() != null) {
            emailInput.getEditText().setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    preferencesManager.setUserEmail(emailInput.getEditText().getText().toString());
                }
            });
        }

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferencesManager.setDarkMode(isChecked);
            requireActivity().recreate();
        });
    }
} 