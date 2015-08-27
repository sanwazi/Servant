package com.example.tek.first.servant.todolist.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tek.first.servant.R;

/**
 * Created by Leon on 8/27/2015.
 */
public class NewItemAddedFragment extends Fragment {

    private ImageButton btnDetailedToDoListItem;
    private EditText editTextInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.todolist_newitem, null);

        btnDetailedToDoListItem = (ImageButton) rootView.findViewById(R.id.todoitemconfirm_btn_activitytodolist);
        editTextInput = (EditText) rootView.findViewById(R.id.todoiteminput_edittext_activitytodolist);

        btnDetailedToDoListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailedNewToDoItemDialogFragment detailedToDoListItemDialog = new DetailedNewToDoItemDialogFragment();
                detailedToDoListItemDialog.show(getFragmentManager(), "DetailedNewToDoItemDialogFragment");
            }
        });
        return rootView;
    }
}
