# Task Scheduler App

A multi-tab Android application that allows users to schedule tasks, view past activities, manage notifications, and personalize their profile with dark/light mode preferences.

## Features

- **Schedule Management**
  - Add new tasks with title, description, and date/time
  - View upcoming tasks
  - Automatic task categorization (future/past)

- **Past Activities**
  - View completed or past tasks
  - Chronological sorting with latest first

- **Notifications**
  - View system notifications
  - Automatic notification management

- **Profile Customization**
  - Edit user information (name, email)
  - Toggle between dark and light themes
  - Persistent preferences storage

## Technical Details

- Built with Kotlin
- Uses SQLite for data persistence
- SharedPreferences for user settings
- Material Design components
- ViewPager2 with TabLayout for navigation
- Vector drawables for icons

## Requirements

- Android Studio Arctic Fox or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.9.22

## Setup Instructions

1. Clone the repository
```bash
git clone https://github.com/yourusername/task-scheduler.git
```

2. Open the project in Android Studio

3. Sync project with Gradle files

4. Run the app on an emulator or physical device

## Architecture

The app follows a modular architecture with:
- Data persistence layer (SQLite + SharedPreferences)
- UI layer with fragments
- Adapter pattern for list management
- Material Design components for UI

## Libraries Used

- AndroidX Core KTX
- AppCompat
- Material Design Components
- ConstraintLayout
- ViewPager2
- Fragment KTX
- Lifecycle Components 