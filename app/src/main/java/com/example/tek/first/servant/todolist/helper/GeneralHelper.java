package com.example.tek.first.servant.todolist.helper;

import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;

public class GeneralHelper {

    public static Long dateAndTimeFormattedToLong(DateModel date, TimeModel time) {
        return Long.parseLong(date.formatToString() + time.formatToString());
    }
}
