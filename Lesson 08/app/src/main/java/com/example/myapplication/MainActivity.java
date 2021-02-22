package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FrameLayout root;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = findViewById(R.id.rootLayout);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.circle_move);
                imageView.startAnimation(anim);
            }
        });

        registerForContextMenu(root);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.anim_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Animation animation = null;
        switch(item.getItemId()) {
            case R.id.TRANSLATE_MENU_ITEM:
                animation = AnimationUtils.loadAnimation(this, R.anim.translate);
                break;
            case R.id.ROTATE_MENU_ITEM:
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
                break;
            case R.id.SEVERAL_MENU_ITEM:
                animation = AnimationUtils.loadAnimation(this, R.anim.several);
                break;
        }
        imageView.startAnimation(animation);
        return super.onContextItemSelected(item);
    }
}