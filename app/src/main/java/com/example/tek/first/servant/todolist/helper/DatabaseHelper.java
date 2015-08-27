package com.example.tek.first.servant.todolist.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tek.first.servant.todolist.model.ToDoItemModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    public static String TODOLIST_ITEM_DEADLINE = "todolist_time_date_set";
    public static String TODOLIST_ITEM_TIME_DATE_CREATED = "todolist_time_date_created";
    public static String TODOLIST_ITEM_CATEGORY = "todolist_category";
    public static String TODOLIST_ITEM_COMPLETE_STATUS = "todolist_complete_status";

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
                TODOLIST_ITEM_DEADLINE + " LONG DEFAULT CURRENT_TIMESTAMP, " +
                TODOLIST_ITEM_TIME_DATE_CREATED + " LONG DEFAULT CURRENT_TIMESTAMP, " +
                TODOLIST_ITEM_CATEGORY + " INTEGER DEFAULT 0, " +
                TODOLIST_ITEM_COMPLETE_STATUS + " INTEGER DEFAULT 0)";
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

    public boolean insertToDoListItem(ToDoItemModel toDoListItem) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TODOLIST_ITEM_TITLE, toDoListItem.getTitle());
        contentValues.put(TODOLIST_ITEM_DESCRIPTION, toDoListItem.getDetailDescription());
        contentValues.put(TODOLIST_ITEM_PRIORITY, toDoListItem.getPriority());
        contentValues.put(TODOLIST_ITEM_TIME_DATE_CREATED, dateFormat.format(toDoListItem.getItemCreatedDateAndTime()));
        contentValues.put(TODOLIST_ITEM_DEADLINE, dateFormat.format(toDoListItem.getItemCreatedDateAndTime()));
        contentValues.put(TODOLIST_ITEM_CATEGORY, toDoListItem.getCategory());
        contentValues.put(TODOLIST_ITEM_COMPLETE_STATUS, toDoListItem.getCompleteStatusCode());
        db.insert(TODOLIST_TABLE_NAME, null, contentValues);
        return true;
    }

//    public boolean insertToDoListItemSetDataAndTime(String title, )

    public ArrayList<ToDoItemModel> getTitlePriorityDeadlineOfAllToDoItemsAsArrayList() {
        ArrayList<ToDoItemModel> toDoListItemsTitlePriorityDeadlineArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String getAllQuery = "SELECT * FROM " + TODOLIST_TABLE_NAME;
        Cursor cursor = db.rawQuery(getAllQuery, null);
        // todo: test whether the 1st item was included
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            String title = cursor.getString(cursor.getColumnIndex(TODOLIST_ITEM_TITLE));
            int priority = cursor.getInt(cursor.getColumnIndex(TODOLIST_ITEM_PRIORITY));
            long deadline = cursor.getLong(cursor.getColumnIndex(TODOLIST_ITEM_DEADLINE));
            ToDoItemModel toDoListItem = new ToDoItemModel(title, priority, deadline);
            toDoListItemsTitlePriorityDeadlineArrayList.add(toDoListItem);
            cursor.moveToNext();
        }
        return toDoListItemsTitlePriorityDeadlineArrayList;
    }

    /**
     * Returns total rows of the table
     *
     * @return
     */
    public int numberOfTotalToDoItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TODOLIST_TABLE_NAME);
        return numRows;
    }

    /**
     * returns total rows of incomplete todolist items
     *
     * @return
     */
//    public int numberOfNotStartedToDoItems(){
//        SQLiteDatabase db = this.getReadableDatabase();
//    }
//
//    public int numberOfIncompleteToDoItems() {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//    }
//
//    public int numberOfCompletedToDoItem() {
//        SQLiteDatabase db = this.getReadableDatabase();
//    }
    public boolean deleteToDoListItem(String title, Long dateAndTimeCreated) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TODOLIST_TABLE_NAME,
                TODOLIST_ITEM_TIME_DATE_CREATED + " =? & " +
                        TODOLIST_ITEM_TITLE + " =?",
                new String[]{dateAndTimeCreated.toString(), title});
        return true;
    }


}
