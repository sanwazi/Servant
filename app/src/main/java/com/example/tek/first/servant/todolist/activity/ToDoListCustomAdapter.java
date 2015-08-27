package com.example.tek.first.servant.todolist.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tek.first.servant.R;
import com.example.tek.first.servant.todolist.model.ToDoListItemModel;

import java.util.ArrayList;

public class ToDoListCustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ToDoListItemModel> toDoListItemsArrayList;

    public ToDoListCustomAdapter(Context context, ArrayList<ToDoListItemModel> toDoListItemsArrayList) {
        this.context = context;
        this.toDoListItemsArrayList = toDoListItemsArrayList;
    }


    @Override
    public int getCount() {
        return toDoListItemsArrayList.size();
    }

    @Override
    public ToDoListItemModel getItem(int position) {
        return toDoListItemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.to_do_list_item, null);
        TextView textViewPriority = (TextView) rootView.findViewById(R.id.todolist_row_priority);
        TextView textViewDeadline = (TextView) rootView.findViewById(R.id.todolist_row_deadline);
        TextView textViewTitle = (TextView) rootView.findViewById(R.id.todolist_row_title);

        textViewTitle.setText(toDoListItemsArrayList.get(position).getTitle());
        textViewPriority.setText(toDoListItemsArrayList.get(position).getPriority());
        textViewDeadline.setText(toDoListItemsArrayList.get(position).getItemDateAndTimeSet().toString());

        return rootView;
    }
}
