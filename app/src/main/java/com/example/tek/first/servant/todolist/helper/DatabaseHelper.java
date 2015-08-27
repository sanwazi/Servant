package com.example.tek.first.servant.todolist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tek.first.servant.todolist.model.ToDoListItemModel;

import java.text.SimpleDateFormat;

/**
 * Created by Leon on 8/26/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String LOG_TAG = DatabaseHelper.class.getSimpleName();

    public static String TODOLIST_DATABASE_NAME = "todolist_database";
    public static String TODOLIST_TABLE_NAME = "todolist_table";

    public static String TODOLIST_ITEM_ID = "todolist_id";
    public static String TODOLIST_ITEM_TITLE = "todolist_title";
    public static String TODOLIST_ITEM_DESCRIPTION = "todolist_description";
    public static String TODOLIST_ITEM_PRIORITY = "todolist_priority";
    public static String TODOLIST_ITEM_TIME_DATE_SET = "todolist_time_date_set";
    public static String TODOLIST_ITEM_TIME_DATE_CREATED = "todolist_time_date_created";
    public static String TODOLIST_ITEM_CATEGORY = "todolist_category";

    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, TODOLIST_DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TODOLIST_TABLE_NAME + " (" +
                TODOLIST_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TODOLIST_ITEM_TITLE + " TEXT NOT NULL, " +
                TODOLIST_ITEM_DESCRIPTION + " TEXT, " +
                TODOLIST_ITEM_PRIORITY + " INTEGER DEFAULT 0, " +
                TODOLIST_ITEM_TIME_DATE_SET + " LONG DEFAULT CURRENT_TIMESTAMP, " +
                TODOLIST_ITEM_TIME_DATE_CREATED + " LONG DEFAULT CURRENT_TIMESTAMP, " +
                TODOLIST_ITEM_CATEGORY + " INTEGER DEFAULT 0)";
        Log.v(LOG_TAG, "createQuery: " + createTableQuery);

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgradeTableQuery = "DROP TABLE IF EXISTS " + TODOLIST_TABLE_NAME;
        Log.v(LOG_TAG, "Upgrade database from " + oldVersion + " to " + newVersion);
        Log.v(LOG_TAG, "upgradeTableQuery: " + upgradeTableQuery);
        db.execSQL(upgradeTableQuery);
    }

    public boolean insertToDoListItem(ToDoListItemModel toDoListItem) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TODOLIST_ITEM_TITLE, toDoListItem.getTitle());
        contentValues.put(TODOLIST_ITEM_DESCRIPTION, toDoListItem.getDetailDescription());
        contentValues.put(TODOLIST_ITEM_PRIORITY, toDoListItem.getPriority());
        contentValues.put(TODOLIST_ITEM_TIME_DATE_CREATED, dateFormat.format(toDoListItem.getItemCreatedDateAndTime()));
        contentValues.put(TODOLIST_ITEM_TIME_DATE_SET, dateFormat.format(toDoListItem.getItemCreatedDateAndTime()));
        contentValues.put(TODOLIST_ITEM_CATEGORY, toDoListItem.getCategory());
        db.insert(TODOLIST_TABLE_NAME, null, contentValues);
        return true;
    }

//    public boolean insertToDoListItemSetDataAndTime(String title, )

    public Cursor getAllToDoItemsAsArrayList(){

    }
}
