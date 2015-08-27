package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.fragment.DatePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.DetailedNewToDoItemDialogFragment;
import com.example.tek.first.servant.todolist.fragment.NewItemAddedFragment;
import com.example.tek.first.servant.todolist.fragment.TimePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.ToDoListDisplayFragment;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.helper.GeneralHelper;
import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;
import com.example.tek.first.servant.todolist.model.ToDoItemModel;

import java.util.ArrayList;


public class ToDoListMainActivity extends Activity
        implements DatePickerDialogFragment.DatePickerDialogListener,
        TimePickerDialogFragment.TimePickerDialogListener,
        DetailedNewToDoItemDialogFragment.OnNewItemAddedListener {

    public static String LOG_TAG = ToDoListMainActivity.class.getSimpleName();

    private DateModel dateSelected = null;
    private TimeModel timeSelected = null;

    private ToDoItemModel toDoItem;
    private DatabaseHelper dbHelper;

    private ArrayList<ToDoItemModel> toDoItemsArrayList;
    private ToDoListCustomAdapter toDoListCustomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        dbHelper = new DatabaseHelper(ToDoListMainActivity.this);
        FragmentManager fragmentManager = getFragmentManager();
        NewItemAddedFragment newItemAddedFragment =
                (NewItemAddedFragment) fragmentManager.findFragmentById(R.id.todolist_newitem);

        toDoItemsArrayList = new ArrayList<>();
        ToDoListDisplayFragment toDoListDisplayFragment
                = (ToDoListDisplayFragment) fragmentManager.findFragmentById(R.id.todolist_displayfragment);

        toDoListCustomAdapter = new ToDoListCustomAdapter(ToDoListMainActivity.this, toDoItemsArrayList);
        toDoListDisplayFragment.setListAdapter(toDoListCustomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todolist_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSelected(DateModel dateSelected) {
        this.dateSelected = dateSelected;
        Toast.makeText(ToDoListMainActivity.this, "Date Selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSelected(TimeModel timeSelected) {
        this.timeSelected = timeSelected;
        Toast.makeText(ToDoListMainActivity.this, "Time Selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewItemAdded(ToDoItemModel toDoItem) {
        Long itemDateAndTimeSet = GeneralHelper.DateAndTimeFormattedToLong(dateSelected, timeSelected);
        toDoItem.setToDoItemDeadline(itemDateAndTimeSet);
        this.toDoItem = toDoItem;
        dbHelper.insertToDoListItem(toDoItem);
        toDoItemsArrayList.add(0, toDoItem);
        toDoListCustomAdapter.notifyDataSetChanged();
    }
}
