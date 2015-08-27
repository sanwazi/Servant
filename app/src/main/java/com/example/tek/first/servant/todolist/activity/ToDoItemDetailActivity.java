package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.helper.GeneralConstants;
import com.example.tek.first.servant.todolist.helper.GeneralHelper;
import com.example.tek.first.servant.todolist.model.ToDoItemModel;

public class ToDoItemDetailActivity extends Activity {

    private static String LOG_TAG = ToDoItemDetailActivity.class.getSimpleName();

    private TextView titleTextView;
    private TextView deadlineTextView;
    private TextView descriptionTextView;
    private TextView priorityTextView;
    private TextView dateAndTimeCreatedTextView;

    private Button btnEdit;
    private Button btnMarkAsComplete;
    private Button btnDelete;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist_todoitem_detail_activity);

        dbHelper = new DatabaseHelper(ToDoItemDetailActivity.this);

        titleTextView = (TextView) findViewById(R.id.textview_title_todoitem_detailactivity);
        deadlineTextView = (TextView) findViewById(R.id.textview_deadline_todoitem_detailactivity);
        descriptionTextView = (TextView) findViewById(R.id.textview_description_todoitem_detailactivity);
        priorityTextView = (TextView) findViewById(R.id.textview_priority_todoitem_detailactivity);
        dateAndTimeCreatedTextView = (TextView) findViewById(R.id.textview_dateandtimecreated_todoitem_detailactivity);

        btnEdit = (Button) findViewById(R.id.btn_edit_detailactivity);
        btnMarkAsComplete = (Button) findViewById(R.id.btn_markascomplete_detailactivity);
        btnDelete = (Button) findViewById(R.id.btn_delete_detailactivity);

        Intent intent = getIntent();
        final ToDoItemModel toDoItem
                = intent.getExtras().getParcelable(GeneralConstants.TO_DO_ITEM_IDENTIFIER);

        refreshToDoItemDetailsPage(toDoItem);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "ToDoItem: " + toDoItem.getTitle() + " will be edited");
            }
        });

        btnMarkAsComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoItem.setCompleteStatusCode(GeneralConstants.TODOLISTITEM_STATUS_COMPLETE);
                Log.v(LOG_TAG, "ToDoItem: " + toDoItem.getTitle() + " is marked as complete");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ToDoItemDetailActivity.this);
                alertDialogBuilder.setMessage("Are you sure to delete ToDoItem: " + toDoItem.getTitle());
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteToDoItem(toDoItem.getTitle(), toDoItem.getItemCreatedDateAndTime());
                        Toast.makeText(ToDoItemDetailActivity.this, "ToDoItem: " + toDoItem.getTitle() + " deleted.", Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void refreshToDoItemDetailsPage(ToDoItemModel toDoItem) {
        titleTextView.setText(toDoItem.getTitle());
        deadlineTextView.setText(GeneralHelper.formatString(toDoItem.getToDoItemDeadline()));
        descriptionTextView.setText(GeneralHelper.formatString(toDoItem.getDetailDescription()));
        priorityTextView.setText(GeneralHelper.formatString(toDoItem.getPriority()));
        dateAndTimeCreatedTextView.setText(GeneralHelper.formatString(toDoItem.getItemCreatedDateAndTime()));
    }
}
