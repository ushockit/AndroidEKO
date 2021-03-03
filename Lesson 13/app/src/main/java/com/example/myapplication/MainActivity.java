package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.fragments.ListItemsFragment;
import com.example.myapplication.fragments.TextFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextFragment.OnFragmentListener {
    ListView lvItems;
    Button btnOpenActivity;
    //ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = findViewById(R.id.lvItems);
        btnOpenActivity = findViewById(R.id.btnOpenActivity);

        //динамическое добавление фрагмента
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, TextFragment.class, null)
                    .commit();

//            Bundle bundle = new Bundle();
//            bundle.putStringArrayList(ListItemsFragment.ARG_LIST_ITEMS, items);
            ArrayList<String> lst = new ArrayList<>();
            lst.add("First");
            lst.add("Second");
            getSupportFragmentManager()
                    .beginTransaction()
                    //.add(R.id.fragment_list, ListItemsFragment.class, null)
                    .add(R.id.fragment_list, ListItemsFragment.newInstance(lst), "ListItemsFragment")
                    .commit();
        }

        //привязка элементов к RecyclerView
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        lvItems.setAdapter(adapter);

        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onAddClick(String text) {
//        items.add(text);
//        adapter.notifyDataSetChanged();
        ListItemsFragment listItemsFragment = (ListItemsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_list);
        listItemsFragment.addItem(text);
    }
}