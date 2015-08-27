package com.example.tek.first.servant.todolist.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.fragment.DatePickerDialogFragment;
import com.example.tek.first.servant.todolist.fragment.TimePickerDialogFragment;
import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
import com.example.tek.first.servant.todolist.model.DateModel;
import com.example.tek.first.servant.todolist.model.TimeModel;
import com.example.tek.first.servant.todolist.model.ToDoListItemModel;

import java.text.SimpleDateFormat;
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

    private String descriptionText;
    private Long currentTimeStamp;
    private Long itemDateAndTimeSet;
    private int priority;
    private int category;


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

    @Override
    public void onDateSelected(DateModel dateSelected) {
        this.dateSelected = dateSelected;
//        Log.v(LOG_TAG, "onDateSelected(), ToDoListMainActivity executed.");
//        Log.v(LOG_TAG, "year, dateSelected, ToDoListMainActivity: " + dateSelected.getYear());
//        Log.v(LOG_TAG, "month, dateSelected, ToDoListMainActivity: " + dateSelected.getMonth());
//        Log.v(LOG_TAG, "day, dateSelected, ToDoListMainActivity: " + dateSelected.getDay());
    }

    @Override
    public void onTimeSelected(TimeModel timeSelected) {
        this.timeSelected = timeSelected;
    }


    class DetailedToDoListItemDialog extends DialogFragment {

        private int timePickerHour = 12;
        private int timePickerMin = 0;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.todolistitemdetail_dialog, null);
            editTextTitle = (EditText) rootView.findViewById(R.id.edittext_title_todolistitem);
            editTextDescription = (EditText) rootView.findViewById(R.id.edittext_description_todolistitem);

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

            if (timeSelected != null) {
                btnTimePicker.setText(timeSelected.toString());
            }

            btnDatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerDialogFragment();
                    dialogFragment.show(getFragmentManager(), "datePicker");
                }
            });

            if (dateSelected != null) {
                btnDatePicker.setText(dateSelected.toString());
            }

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titleText = editTextTitle.getText().toString();
                    if (titleText != null && titleText.length() > 0) {
                        descriptionText = editTextDescription.getText().toString();
                        currentTimeStamp =
                                Long.parseLong(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
                        // todo: get all data info
                        priority = 0;
                        itemDateAndTimeSet = 0L;
                        category = 0;

                        ToDoListItemModel toDoListItem = new ToDoListItemModel(titleText, priority, descriptionText, currentTimeStamp, itemDateAndTimeSet, category);
                        dbHelper.insertToDoListItem(toDoListItem);
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
