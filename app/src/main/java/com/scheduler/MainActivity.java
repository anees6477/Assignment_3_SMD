package com.scheduler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.scheduler.adapters.ViewPagerAdapter;
import com.scheduler.utils.PreferencesManager;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesManager = new PreferencesManager(this);
        setupTheme();
        setupViewPager();
    }

    private void setupTheme() {
        int nightMode = preferencesManager.isDarkMode() ? 
            AppCompatDelegate.MODE_NIGHT_YES : 
            AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    private void setupViewPager() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Schedule");
                    tab.setIcon(R.drawable.ic_schedule);
                    break;
                case 1:
                    tab.setText("Past");
                    tab.setIcon(R.drawable.ic_history);
                    break;
                case 2:
                    tab.setText("Notifications");
                    tab.setIcon(R.drawable.ic_notifications);
                    break;
                case 3:
                    tab.setText("Profile");
                    tab.setIcon(R.drawable.ic_profile);
                    break;
                default:
                    tab.setText("");
                    tab.setIcon(0);
                    break;
            }
        }).attach();
    }
} 