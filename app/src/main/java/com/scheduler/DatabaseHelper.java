package com.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scheduler.models.Task;
import com.scheduler.models.Notification;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SchedulerDB";
    private static final int DATABASE_VERSION = 1;

    // Tasks Table
    private static final String TABLE_TASKS = "tasks";
    private static final String COLUMN_TASK_ID = "id";
    private static final String COLUMN_TASK_TITLE = "title";
    private static final String COLUMN_TASK_DESCRIPTION = "description";
    private static final String COLUMN_TASK_DATETIME = "datetime";
    private static final String COLUMN_TASK_STATUS = "status";

    // Notifications Table
    private static final String TABLE_NOTIFICATIONS = "notifications";
    private static final String COLUMN_NOTIFICATION_ID = "id";
    private static final String COLUMN_NOTIFICATION_MESSAGE = "message";
    private static final String COLUMN_NOTIFICATION_DATETIME = "datetime";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Tasks Table
        String createTasksTable = "CREATE TABLE " + TABLE_TASKS + " (" +
                COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK_TITLE + " TEXT NOT NULL, " +
                COLUMN_TASK_DESCRIPTION + " TEXT, " +
                COLUMN_TASK_DATETIME + " TEXT NOT NULL, " +
                COLUMN_TASK_STATUS + " TEXT NOT NULL)";

        // Create Notifications Table
        String createNotificationsTable = "CREATE TABLE " + TABLE_NOTIFICATIONS + " (" +
                COLUMN_NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTIFICATION_MESSAGE + " TEXT NOT NULL, " +
                COLUMN_NOTIFICATION_DATETIME + " TEXT NOT NULL)";

        db.execSQL(createTasksTable);
        db.execSQL(createNotificationsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        onCreate(db);
    }

    // Task CRUD Operations
    public long insertTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_TITLE, task.getTitle());
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
        values.put(COLUMN_TASK_DATETIME, task.getDatetime());
        values.put(COLUMN_TASK_STATUS, task.getStatus());
        return db.insert(TABLE_TASKS, null, values);
    }

    public List<Task> getFutureTasks() {
        List<Task> tasks = new ArrayList<>();
        long currentDateTime = System.currentTimeMillis();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_TASKS,
                null,
                COLUMN_TASK_DATETIME + " > ?",
                new String[]{String.valueOf(currentDateTime)},
                null,
                null,
                COLUMN_TASK_DATETIME + " ASC"
        );

        while (cursor.moveToNext()) {
            Task task = new Task(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASK_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DATETIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_STATUS))
            );
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    public List<Task> getPastTasks() {
        List<Task> tasks = new ArrayList<>();
        long currentDateTime = System.currentTimeMillis();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_TASKS,
                null,
                COLUMN_TASK_DATETIME + " <= ?",
                new String[]{String.valueOf(currentDateTime)},
                null,
                null,
                COLUMN_TASK_DATETIME + " DESC"
        );

        while (cursor.moveToNext()) {
            Task task = new Task(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TASK_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_DATETIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK_STATUS))
            );
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    // Notification Operations
    public long insertNotification(Notification notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(COLUMN_NOTIFICATION_DATETIME, notification.getDatetime());
        return db.insert(TABLE_NOTIFICATIONS, null, values);
    }

    public List<Notification> getNotifications() {
        List<Notification> notifications = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NOTIFICATIONS,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NOTIFICATION_DATETIME + " DESC"
        );

        while (cursor.moveToNext()) {
            Notification notification = new Notification(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NOTIFICATION_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTIFICATION_MESSAGE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTIFICATION_DATETIME))
            );
            notifications.add(notification);
        }
        cursor.close();
        return notifications;
    }
} 