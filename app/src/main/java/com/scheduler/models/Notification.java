package com.scheduler.models;

import java.util.Objects;

public class Notification {
    private int id;
    private String message;
    private String datetime;

    public Notification() {
        this.id = 0;
    }

    public Notification(int id, String message, String datetime) {
        this.id = id;
        this.message = message;
        this.datetime = datetime;
    }

    public Notification(String message, String datetime) {
        this(0, message, datetime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id &&
                Objects.equals(message, that.message) &&
                Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, datetime);
    }
} 