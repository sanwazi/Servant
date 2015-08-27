package com.example.tek.first.servant.todolist.helper;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;

public class GeneralHelper {
    private static String LOG_TAG = GeneralHelper.class.getSimpleName();

    public static Long DateAndTimeFormattedToLong(DateModel date, TimeModel time) {
        if (date != null && time != null)
            return Long.parseLong(date.formatToString() + time.formatToString());
        else {
            Log.e(LOG_TAG, "Date or time might be null");
            return 0L;
        }
    }

    /**
     * 0: not started
     * 1: incompleted
     * 2: completed
     */
    public enum CompleteStatus implements Parcelable {
        NOTSTARTED(0),
        INCOMPLETED(1),
        COMPLETED(2);

        private int statusCode;

        CompleteStatus(int statusCode) {
            this.statusCode = statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
            dest.writeInt(statusCode);
        }

        // todo: what is this method used for? Possible explanation might be found at Parcelable class
        public static final Parcelable.Creator<CompleteStatus> CREATOR = new Parcelable.Creator<CompleteStatus>() {

            public CompleteStatus createFromParcel(Parcel in) {
                CompleteStatus completeStatus = CompleteStatus.values()[in.readInt()];
                completeStatus.setStatusCode(in.readInt());
                return completeStatus;
            }

            public CompleteStatus[] newArray(int size) {
                return new CompleteStatus[size];
            }
        };
    }
}
