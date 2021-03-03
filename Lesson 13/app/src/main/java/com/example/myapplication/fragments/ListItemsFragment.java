package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class ListItemsFragment extends Fragment {

    static final String ARG_ITEMS = "items";

    ArrayList<String> items = new ArrayList<>();
    {
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
    }
    ArrayAdapter<String> adapter;

    // TODO: Rename and change types of parameters

    public ListItemsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListItemsFragment newInstance(ArrayList<String> items) {
        ListItemsFragment fragment = new ListItemsFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_ITEMS, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            items = getArguments().getStringArrayList(ARG_ITEMS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);

        ListView lvItems = view.findViewById(R.id.lvItems);

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(adapter);

        return view;
    }

    public void addItem(String item) {
        items.add(item);
        adapter.notifyDataSetChanged();
    }
}