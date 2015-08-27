package com.example.tek.first.servant.todolist.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.tek.first.servant.todolist.activity.ToDoItemDetailActivity;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.helper.GeneralConstants;
import com.example.tek.first.servant.todolist.model.ToDoItemModel;

import java.util.ArrayList;

public class ToDoListDisplayFragment extends ListFragment implements AdapterView.OnItemSelectedListener {

    private DatabaseHelper dbHelper;

    @Override
    public void onStart() {
        super.onStart();
        dbHelper = new DatabaseHelper(getActivity());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<ToDoItemModel> toDoItemsArrayList = dbHelper.getAllToDoItemsAsArrayList();
        Intent intent = new Intent(getActivity(), ToDoItemDetailActivity.class);
        intent.putExtra(GeneralConstants.ALL_TO_DO_ITEMS_IDENTIFIER, toDoItemsArrayList.get(position));
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
