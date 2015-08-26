package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.fragment.DetailedToDoListItemDialog;

public class ToDoListMainActivity extends Activity {

    private ImageButton btnDetailedToDoListItem;
    private EditText editTextInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        btnDetailedToDoListItem = (ImageButton) findViewById(R.id.todoitemconfirm_btn_activitytodolist);
        editTextInput = (EditText) findViewById(R.id.todoiteminput_edittext_activitytodolist);

        btnDetailedToDoListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DetailedToDoListItemDialog detailedToDoListItemDialog = new DetailedToDoListItemDialog();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace();
                DetailedToDoListItemDialog detailedToDoListItemDialog = new DetailedToDoListItemDialog();
                detailedToDoListItemDialog.show(getFragmentManager(), "DetailedToDoListItemDialog");
            }
        });
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
}
