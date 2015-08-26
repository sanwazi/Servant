package com.example.tek.first.servant.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tek.first.servant.R;

public class ToDoListMainActivity extends Activity {

    private ImageButton btnConfirm;
    private EditText editTextInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        btnConfirm = (ImageButton) findViewById(R.id.todoitemconfirm_btn_activitytodolist);
        editTextInput = (EditText) findViewById(R.id.todoiteminput_edittext_activitytodolist);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void createToDoListItemDetail() {
        Dialog dialog = new Dialog(this);

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
