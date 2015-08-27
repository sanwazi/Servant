//package com.example.tek.first.servant.todolist.fragment;
//
//import android.app.DialogFragment;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.tek.first.servant.R;
//import com.example.tek.first.servant.todolist.helper.DatabaseHelper;
//import com.example.tek.first.servant.todolist.helper.GeneralConstants;
//import com.example.tek.first.servant.todolist.model.DateModel;
//import com.example.tek.first.servant.todolist.model.TimeModel;
//import com.example.tek.first.servant.todolist.model.ToDoListItemModel;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//public class DetailedToDoListItemDialog extends DialogFragment
//        implements DatePickerDialogFragment.DatePickerDialogListener {
//
//    private static String LOG_TAG = DetailedToDoListItemDialog.class.getSimpleName();
//
//    private EditText editTextTitle;
//    private EditText editTextDescription;
//    private Spinner spinnerPriority;
//    private Button btnDatePicker;
//    private Button btnTimePicker;
//    private Button btnConfirm;
//
//    private String descriptionText;
//    private Long currentTimeStamp;
//    private Long itemDateAndTimeSet;
//    private int priority;
//    private int category;
//
//    private Bundle bundle;
//    private TimeModel timeSet;
//    private DateModel dateSet;
//    private DatabaseHelper dbHelper;
//
//    private int timePickerHour = 12;
//    private int timePickerMin = 0;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.todolistitemdetail_dialog, null);
//        editTextTitle = (EditText) rootView.findViewById(R.id.edittext_title_todolistitem);
//        editTextDescription = (EditText) rootView.findViewById(R.id.edittext_description_todolistitem);
//
//        dbHelper = new DatabaseHelper(getActivity());
//
//        spinnerPriority = (Spinner) rootView.findViewById(R.id.spinner_priority_todolistitem);
//        btnTimePicker = (Button) rootView.findViewById(R.id.todolist_btn_selecttime);
//        btnDatePicker = (Button) rootView.findViewById(R.id.todolist_btn_selectdate);
//        btnConfirm = (Button) rootView.findViewById(R.id.btn_confirm_dialog);
//
//
//        btnTimePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment dialogFragment = new TimePickerDialogFragment();
//                dialogFragment.show(getFragmentManager(), "timePicker");
//            }
//        });
//
//        btnDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment dialogFragment = new DatePickerDialogFragment();
//                dialogFragment.show(getFragmentManager(), "datePicker");
//            }
//        });
//
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String titleText = editTextTitle.getText().toString();
//                if (titleText != null && titleText.length() > 0) {
//                    descriptionText = editTextDescription.getText().toString();
//                    currentTimeStamp =
//                            Long.parseLong(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
//                    // todo: get all data info
//                    priority = 0;
//                    itemDateAndTimeSet = 0L;
//                    category = 0;
//
//                    ToDoListItemModel toDoListItem = new ToDoListItemModel(titleText, priority, descriptionText, currentTimeStamp, itemDateAndTimeSet, category);
//                    dbHelper.insertToDoListItem(toDoListItem);
////                    Intent detailedInfoIntent = new Intent(getActivity(), ToDoListMainActivity.class);
////                    detailedInfoIntent.putExtra(GeneralConstants.TODOLISTITEM_IDENTIFIER, toDoListItem);
////                    startActivity(detailedInfoIntent);
//                } else {
//                    Toast.makeText(getActivity(), "Please input a title", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        return rootView;
//    }
//
//    @Override
//    public void onDateSelected(DateModel dateSelected) {
//        dateSet = dateSelected;
//        Log.v(LOG_TAG, "onDateSelected(), DetailedToDoListItemDialog executed.");
//    }
//}
