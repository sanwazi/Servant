package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.fragment.dialog.DatePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.dialog.DetailedNewToDoItemDialogFragment;
import com.example.tek.first.servant.todolist.fragment.NewItemAddedFragment;
import com.example.tek.first.servant.todolist.fragment.dialog.TimePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.ToDoListDisplayFragment;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.helper.GeneralConstants;
import com.example.tek.first.servant.todolist.helper.GeneralHelper;
import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;
import com.example.tek.first.servant.todolist.model.ToDoItemModel;

import java.util.ArrayList;


public class ToDoListMainActivity extends Activity
        implements DatePickerDialogFragment.DatePickerDialogListener,
        TimePickerDialogFragment.TimePickerDialogListener,
        DetailedNewToDoItemDialogFragment.OnNewItemAddedListener,
        NewItemAddedFragment.OnNewSimpleItemAddedListener {

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

        toDoItemsArrayList = new ArrayList<>();
        if (savedInstanceState != null) {
            toDoItemsArrayList = savedInstanceState.getParcelableArrayList(GeneralConstants.SAVEINSTANCESTATE_TODOITEMS_ARRAYLIST_IDENTIFIER);
        } else {
            dbHelper = new DatabaseHelper(ToDoListMainActivity.this);
            toDoItemsArrayList = dbHelper.getAllToDoItemsAsArrayList();
        }

        FragmentManager fragmentManager = getFragmentManager();
        NewItemAddedFragment newItemAddedFragment =
                (NewItemAddedFragment) fragmentManager.findFragmentById(R.id.todolist_newitem);

        ToDoListDisplayFragment toDoListDisplayFragment
                = (ToDoListDisplayFragment) fragmentManager.findFragmentById(R.id.todolist_displayfragment);

        toDoListCustomAdapter = new ToDoListCustomAdapter(ToDoListMainActivity.this, toDoItemsArrayList);
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(GeneralConstants.TO_DO_ITEMS_ARRAYLIST_IDENTIFIER, toDoItemsArrayList);
//        toDoListDisplayFragment.setArguments(bundle);
        toDoListDisplayFragment.setListAdapter(toDoListCustomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todolist_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(
                GeneralConstants.SAVEINSTANCESTATE_TODOITEMS_ARRAYLIST_IDENTIFIER, toDoItemsArrayList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSelected(DateModel dateSelected) {
        this.dateSelected = dateSelected;
        Toast.makeText(ToDoListMainActivity.this, "Date set: " + dateSelected.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSelected(TimeModel timeSelected) {
        this.timeSelected = timeSelected;
        Toast.makeText(ToDoListMainActivity.this, "Time set: " + timeSelected.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewItemAdded(ToDoItemModel toDoItem) {
        Long deadline = GeneralHelper.dateAndTimeFormattedToLong(dateSelected, timeSelected);
        Log.v(LOG_TAG, "deadline, onNewItemAdded, ToDoListMainActivity: " + deadline);
        toDoItem.setToDoItemDeadline(deadline);
//        this.toDoItem = toDoItem;
        dbHelper.insertToDoListItem(toDoItem);
        toDoItemsArrayList.add(0, toDoItem);
        toDoListCustomAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewSimpleItemAdded(ToDoItemModel newSimpleItem) {
        dbHelper.insertToDoListItem(newSimpleItem);
        toDoItemsArrayList.add(0, newSimpleItem);
        toDoListCustomAdapter.notifyDataSetChanged();
    }
}