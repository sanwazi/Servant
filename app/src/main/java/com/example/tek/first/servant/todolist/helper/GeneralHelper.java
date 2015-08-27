package com.example.tek.first.servant.todolist.helper;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;

public class GeneralHelper {
    private static String LOG_TAG = GeneralHelper.class.getSimpleName();

    public static Long dateAndTimeFormattedToLong(DateModel date, TimeModel time) {
        if (date != null && time != null)
            return Long.parseLong(date.formatDateToString() + time.formatTimeToString());
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
    public enum CompletionStatus implements Parcelable {
        NOTSTARTED(0),
        INCOMPLETED(1),
        COMPLETED(2);

        private int statusCode;

        CompletionStatus(int statusCode) {
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
        public static final Parcelable.Creator<CompletionStatus> CREATOR = new Parcelable.Creator<CompletionStatus>() {

            public CompletionStatus createFromParcel(Parcel in) {
                CompletionStatus completionStatus = CompletionStatus.values()[in.readInt()];
                completionStatus.setStatusCode(in.readInt());
                return completionStatus;
            }

            public CompletionStatus[] newArray(int size) {
                return new CompletionStatus[size];
            }
        };
    }

    public static CompletionStatus completionStatusCodeToCompletationStatus(int completionStatusCode) {
        switch (completionStatusCode) {
            case 0:
                return CompletionStatus.NOTSTARTED;
            case 1:
                return CompletionStatus.INCOMPLETED;
            case 2:
                return CompletionStatus.COMPLETED;
        }
        return CompletionStatus.NOTSTARTED;
    }

    public static String formatString(String text) {
        if (text == null || text.length() == 0) {
            return "";
        } else
            return text;
    }

    public static String formatString(Long text) {
        return text.toString();
    }

    public static String formatString(int text) {
        return Integer.toString(text);
    }
}
