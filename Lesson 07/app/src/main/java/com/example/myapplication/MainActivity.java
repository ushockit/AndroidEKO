package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowPopup = findViewById(R.id.btnShowPopup);

        btnShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click show popup", Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.popup_menu);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Click on item", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        Toast.makeText(MainActivity.this, "Dismiss popup menu", Toast.LENGTH_SHORT).show();
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem dynamicItem = menu.add(R.id.checkedGroup, 100, 3, "Dynamic item");
        dynamicItem.setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_item1:
//                Toast.makeText(this, "Action 1", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.action_item2:
                item.setChecked(!item.isChecked());
                Toast.makeText(this, "Action 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_item3:
                Toast.makeText(this, String.format("Action 3 - %s", item.isChecked() ? "checked" : "unchecked"), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onAction1Click(@NonNull MenuItem item) {
        Toast.makeText(this, "Action 1", Toast.LENGTH_SHORT).show();
    }
}