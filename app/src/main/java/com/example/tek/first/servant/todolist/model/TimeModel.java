package com.example.tek.first.servant.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leon on 8/26/2015.
 */
public class TimeModel implements Parcelable {

    private int hour;
    private int minute;

    public TimeModel(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    protected TimeModel(Parcel in) {
        hour = in.readInt();
        minute = in.readInt();
    }

    public static final Creator<TimeModel> CREATOR = new Creator<TimeModel>() {
        @Override
        public TimeModel createFromParcel(Parcel in) {
            return new TimeModel(in);
        }

        @Override
        public TimeModel[] newArray(int size) {
            return new TimeModel[size];
        }
    };

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hour);
        dest.writeInt(minute);
    }

    @Override
    public String toString() {
        return "TimeModel{" +
                "hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    public String formatToString() {
        return Integer.toString(hour) + Integer.toString(minute);
    }

    public Long formatToLong() {
        return Long.parseLong(formatToString());
    }
}
