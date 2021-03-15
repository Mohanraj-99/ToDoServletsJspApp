package com.mohanraj.ToDo_Application.model;

import java.io.Serializable;

public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String topic;
    private String description;
    private String status;

    public Todo(String topic, String description, String status) {
        super();
        this.topic = topic;
        this.description = description;
        this.status = status;
    }

    public Todo(int id, String topic, String description, String status) {
        this.id = id;
        this.topic = topic;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
