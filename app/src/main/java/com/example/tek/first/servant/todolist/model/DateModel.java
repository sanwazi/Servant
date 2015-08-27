package com.example.tek.first.servant.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leon on 8/26/2015.
 */
public class DateModel implements Parcelable {
    private int month;
    private int day;
    private int year;

    public DateModel(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    protected DateModel(Parcel in) {
        month = in.readInt();
        day = in.readInt();
        year = in.readInt();
    }

    public static final Creator<DateModel> CREATOR = new Creator<DateModel>() {
        @Override
        public DateModel createFromParcel(Parcel in) {
            return new DateModel(in);
        }

        @Override
        public DateModel[] newArray(int size) {
            return new DateModel[size];
        }
    };

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(year);
    }

    @Override
    public String toString() {

        return "DateModel{" +
                "month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }

    public String formatToString() {
        return Integer.toString(year) + Integer.toString(month) + Integer.toString(day);
    }

    public Long formatToLong() {
        return Long.parseLong(formatToString());
    }
}
