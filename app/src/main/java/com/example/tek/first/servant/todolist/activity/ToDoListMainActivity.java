package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.fragment.DatePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.TimePickerDialogFragment;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.helper.GeneralHelper;
import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;
import com.example.tek.first.servant.todolist.model.ToDoListItemModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ToDoListMainActivity extends Activity
        implements DatePickerDialogFragment.DatePickerDialogListener,
        TimePickerDialogFragment.TimePickerDialogListener {

    public static String LOG_TAG = ToDoListMainActivity.class.getSimpleName();

    private ImageButton btnDetailedToDoListItem;
    private EditText editTextInput;

    private DateModel dateSelected = null;
    private TimeModel timeSelected = null;

    private DatabaseHelper dbHelper;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerPriority;
    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btnConfirm;
    private Button btnClear;

    private String title = null;
    private String descriptionText = null;
    private Long currentTimeStamp = 0L;
    private Long itemDateAndTimeSet = 0L;
    private int priority = 1;
    private int category = 0;

    private ArrayList<ToDoListItemModel> toDoItemsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        btnDetailedToDoListItem = (ImageButton) findViewById(R.id.todoitemconfirm_btn_activitytodolist);
        editTextInput = (EditText) findViewById(R.id.todoiteminput_edittext_activitytodolist);

        btnDetailedToDoListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailedToDoListItemDialog detailedToDoListItemDialog = new DetailedToDoListItemDialog();
                detailedToDoListItemDialog.show(getFragmentManager(), "DetailedToDoListItemDialog");
            }
        });

        dbHelper = new DatabaseHelper(ToDoListMainActivity.this);
        toDoItemsArrayList = new ArrayList<>();
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
//        Log.v(LOG_TAG, "onDateSelected(), ToDoListMainActivity executed.");
//        Log.v(LOG_TAG, "year, dateSelected, ToDoListMainActivity: " + dateSelected.getYear());
//        Log.v(LOG_TAG, "month, dateSelected, ToDoListMainActivity: " + dateSelected.getMonth());
//        Log.v(LOG_TAG, "day, dateSelected, ToDoListMainActivity: " + dateSelected.getDay());
        Toast.makeText(ToDoListMainActivity.this, "Date Selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSelected(TimeModel timeSelected) {
        this.timeSelected = timeSelected;
        Toast.makeText(ToDoListMainActivity.this, "Time Selected", Toast.LENGTH_SHORT).show();
    }

    class DetailedToDoListItemDialog extends DialogFragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.todolistitemdetail_dialog, null);
            editTextTitle = (EditText) rootView.findViewById(R.id.edittext_title_todolistitem);
            editTextDescription = (EditText) rootView.findViewById(R.id.edittext_description_todolistitem);

            btnClear = (Button) rootView.findViewById(R.id.btn_clear_dialog);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View rootView = inflater.inflate(R.layout.todolist_textview, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(rootView).setTitle(R.string.clear_confirmation_dialog_text)
                            .setPositiveButton(R.string.todolist_clear_text, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editTextTitle.setText("");
                                    editTextDescription.setText("");
                                }
                            }).setNegativeButton(R.string.todolist_cancel_text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create();
                }
            });

            spinnerPriority = (Spinner) rootView.findViewById(R.id.spinner_priority_todolistitem);
            ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.priority_level, android.R.layout.simple_spinner_item);

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerPriority.setAdapter(spinnerAdapter);

            spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    priority = position + 1;
                    Toast.makeText(getActivity(), "Priority: " + priority, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    priority = 1;
                }
            });

            dbHelper = new DatabaseHelper(getActivity());

            spinnerPriority = (Spinner) rootView.findViewById(R.id.spinner_priority_todolistitem);
            btnTimePicker = (Button) rootView.findViewById(R.id.todolist_btn_selecttime);
            btnDatePicker = (Button) rootView.findViewById(R.id.todolist_btn_selectdate);
            btnConfirm = (Button) rootView.findViewById(R.id.btn_confirm_dialog);

            btnTimePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new TimePickerDialogFragment();
                    dialogFragment.show(getFragmentManager(), "timePicker");
                }
            });

//            if (timeSelected != null) {
//                btnTimePicker.setText(timeSelected.toString());
//            }

            btnDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerDialogFragment();
                    dialogFragment.show(getFragmentManager(), "datePicker");
                }
            });

//            if (dateSelected != null) {
//                btnDatePicker.setText(dateSelected.toString());
//            }

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    title = editTextTitle.getText().toString();
                    if (title != null && title.length() > 0) {
                        descriptionText = editTextDescription.getText().toString();
                        currentTimeStamp =
                                Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));
                        Log.v(LOG_TAG, "currentTimeStamp: " + currentTimeStamp);
                        itemDateAndTimeSet = GeneralHelper.dateAndTimeFormattedToLong(dateSelected, timeSelected);
                        category = 0;

                        ToDoListItemModel toDoListItem = new ToDoListItemModel(title, priority, descriptionText, currentTimeStamp, itemDateAndTimeSet, category);
                        dbHelper.insertToDoListItem(toDoListItem);
                        dismiss();
//                    Intent detailedInfoIntent = new Intent(getActivity(), ToDoListMainActivity.class);
//                    detailedInfoIntent.putExtra(GeneralConstants.TODOLISTITEM_IDENTIFIER, toDoListItem);
//                    startActivity(detailedInfoIntent);
                    } else {
                        Toast.makeText(getActivity(), "Please input a title", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            return rootView;
        }
    }
}
