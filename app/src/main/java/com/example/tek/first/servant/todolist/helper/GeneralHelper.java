package com.example.tek.first.servant.todolist.helper;

import android.util.Log;

import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;

public class GeneralHelper {
    private static String LOG_TAG = GeneralHelper.class.getSimpleName();

    public static Long dateAndTimeFormattedToLong(DateModel date, TimeModel time) {
        if (date != null && time != null)
            return Long.parseLong(date.formatToString() + time.formatToString());
        else {
            Log.e(LOG_TAG, "Date or time might be null");
            return 0L;
        }
    }
}
