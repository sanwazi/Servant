package com.example.tek.first.servant.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDoListItemModel implements Parcelable {
    private String title;
    private int priority;
    private String detailDescription;
    private Long itemCreatedDateAndTime;
    private Long itemDateAndTimeSet;
    private int category;

    public ToDoListItemModel(String title, int priority, String detailDescription, Long itemCreatedDateAndTime, Long itemDateAndTimeSet, int category) {
        this.title = title;
        this.priority = priority;
        this.detailDescription = detailDescription;
        this.itemCreatedDateAndTime = itemCreatedDateAndTime;
        this.itemDateAndTimeSet = itemDateAndTimeSet;
        this.category = category;
    }

    protected ToDoListItemModel(Parcel in) {
        title = in.readString();
        priority = in.readInt();
        detailDescription = in.readString();
        itemCreatedDateAndTime = in.readLong();
        itemDateAndTimeSet = in.readLong();
        category = in.readInt();
    }

    public static final Creator<ToDoListItemModel> CREATOR = new Creator<ToDoListItemModel>() {
        @Override
        public ToDoListItemModel createFromParcel(Parcel in) {
            return new ToDoListItemModel(in);
        }

        @Override
        public ToDoListItemModel[] newArray(int size) {
            return new ToDoListItemModel[size];
        }
    };

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setItemCreatedDateAndTime(Long itemCreatedDateAndTime) {
        this.itemCreatedDateAndTime = itemCreatedDateAndTime;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public void setItemDateAndTimeSet(Long itemDateAndTimeSet) {
        this.itemDateAndTimeSet = itemDateAndTimeSet;
    }

    public String getTitle() {

        return title;
    }

    public Long getItemCreatedDateAndTime() {
        return itemCreatedDateAndTime;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public Long getItemDateAndTimeSet() {
        return itemDateAndTimeSet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(priority);
        dest.writeString(detailDescription);
        dest.writeInt(category);
        dest.writeLong(itemCreatedDateAndTime);
        dest.writeLong(itemDateAndTimeSet);
    }
}
