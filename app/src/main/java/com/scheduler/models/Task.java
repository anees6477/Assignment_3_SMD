package com.scheduler.models;

import java.util.Objects;

public class Task {
    private int id;
    private String title;
    private String description;
    private String datetime;
    private String status;

    public Task() {
        this.id = 0;
    }

    public Task(int id, String title, String description, String datetime, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.status = status;
    }

    public Task(String title, String description, String datetime, String status) {
        this(0, title, description, datetime, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(datetime, task.datetime) &&
                Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, datetime, status);
    }
} 