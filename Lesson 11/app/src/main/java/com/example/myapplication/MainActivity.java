package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.tasks.MyTask;
import com.squareup.picasso.Picasso;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button btnStartTask, btnGetResult, btnStopTask, btnLoadImg;
    TextView textView, tvResult;
    ImageView imageView;
    MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStartTask = findViewById(R.id.btnStartTask);
        textView = findViewById(R.id.textView);
        btnGetResult = findViewById(R.id.btnGetResult);
        tvResult = findViewById(R.id.tvResult);
        btnStopTask = findViewById(R.id.btnStopTask);
        btnLoadImg = findViewById(R.id.btnLoadImage);
        imageView = findViewById(R.id.imageView);


        //Запуск задачи
        btnStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask = new MyTask("Task 1", textView);
                //пропускате только одну задачу на выполнение (т.е. другие задачи будут дожидаться завершения активной задачи)
                myTask.execute(10, 15);
                //используется когда несколько задач должны работать одновременно
                //myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        //Получение результата
        btnGetResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTask != null && myTask.getStatus() == AsyncTask.Status.FINISHED) {
                    try {
                        String result = myTask.get();
                        tvResult.setText(result);
                    } catch (ExecutionException | InterruptedException | CancellationException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        //Остановка задачи
        btnStopTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTask != null && myTask.getStatus() == AsyncTask.Status.RUNNING) {
                    myTask.cancel(true);
                }
            }
        });

        //Загрузка изображения
        btnLoadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get()
                        .load("https://www.osp.ru/FileStorage/DOCUMENTS_ILLUSTRATIONS/13224756/original.jpg")
//                        .resize(100, 100)
//                        .centerCrop()
                        .into(imageView);
            }
        });
    }
}