package com.example.tek.first.servant.todolist.model;


import java.util.Date;

public class ToDoItemModel {
    private String title;
    private Date itemCreatedDate;
    private String detailDescription;
    private Date notificationDate;

    public ToDoItemModel(String title, Date itemCreatedDate, String detailDescription, Date notificationDate) {
        this.title = title;
        this.itemCreatedDate = itemCreatedDate;
        this.detailDescription = detailDescription;
        this.notificationDate = notificationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItemCreatedDate(Date itemCreatedDate) {
        this.itemCreatedDate = itemCreatedDate;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getTitle() {

        return title;
    }

    public Date getItemCreatedDate() {
        return itemCreatedDate;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }
}
