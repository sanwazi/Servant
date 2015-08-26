package com.example.tek.first.servant.todolist.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tek.first.servant.R;

/**
 * Created by Leon on 8/26/2015.
 */
public class DetailedToDoListItemDialog extends Fragment {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerPriority;
    private Button btnDatePicker;
    private Button btnTimePicker;
    private Button btnConfirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.to_do_list_item, null);
        editTextTitle = (EditText) rootView.findViewById(R.id.edittext_title_todolistitem);
        editTextDescription = (EditText) rootView.findViewById(R.id.edittext_description_todolistitem);

        spinnerPriority = (Spinner) rootView.findViewById(R.id.spinner_priority_todolistitem);
        btnTimePicker = (Button) rootView.findViewById(R.id.todolist_btn_selecttime);
        btnDatePicker = (Button) rootView.findViewById(R.id.todolist_btn_selectdate);
        btnConfirm = (Button) rootView.findViewById(R.id.btn_confirm_dialog);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
