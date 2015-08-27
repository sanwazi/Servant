package com.example.tek.first.servant.todolist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leon on 8/26/2015.
 */
public class DateModel implements Parcelable {
    private int Month;
    private int Day;
    private int Year;

    public DateModel(int month, int day, int year) {
        Month = month;
        Day = day;
        Year = year;
    }

    protected DateModel(Parcel in) {
        Month = in.readInt();
        Day = in.readInt();
        Year = in.readInt();
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
        return Month;
    }

    public int getDay() {
        return Day;
    }

    public int getYear() {
        return Year;
    }

    public void setMonth(int month) {
        Month = month;
    }

    public void setDay(int day) {
        Day = day;
    }

    public void setYear(int year) {
        Year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Month);
        dest.writeInt(Day);
        dest.writeInt(Year);
    }

    @Override
    public String toString() {
        return "DateModel{" +
                "Month=" + Month +
                ", Day=" + Day +
                ", Year=" + Year +
                '}';
    }
}
