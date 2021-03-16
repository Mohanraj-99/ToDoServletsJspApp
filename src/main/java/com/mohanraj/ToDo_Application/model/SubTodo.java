package com.mohanraj.ToDo_Application.model;

public class SubTodo {

    private int id;
    private String subTopic;
    private String description;
    private String status;
    private int parentId;

    public SubTodo(int id, String subTopic, String description, String status ,int parentId ) {
        this.id = id;
        this.subTopic = subTopic;
        this.description = description;
        this.status = status;
        this.parentId = parentId;
    }

    public SubTodo(String subTopic, String description, String status ,int parentId) {
        this.subTopic = subTopic;
        this.description = description;
        this.status = status;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
