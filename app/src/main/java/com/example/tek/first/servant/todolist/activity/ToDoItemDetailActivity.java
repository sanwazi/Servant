package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.helper.GeneralConstants;
import com.example.tek.first.servant.todolist.model.ToDoItemModel;

import java.util.ArrayList;

public class ToDoItemDetailActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_todoitem_detail_activity);
        Intent intent = getIntent();
        ToDoItemModel toDoItem
                = intent.getExtras().getParcelable(GeneralConstants.TO_DO_ITEM_IDENTIFIER);
    }

    private void refreshToDoItemDetailsPage(ToDoItemModel toDoItem) {

    }
}
