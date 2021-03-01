package com.example.myapplication.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MyTask extends AsyncTask<Integer, Integer, String> {
    private static final String TAG = "MyTaskTag";
    private TextView textView;
    private String name;

    public MyTask(String name, TextView textView) {
        this.name = name;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        int from = params[0];
        int to = params[1];
        int i;
        for (i = from; i <= to; i++){
            //был запрос на отмену задачи
            if (isCancelled()) {
                textView.setText("Task is stopped!");
                break;
            }
            try {
                publishProgress(i);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return String.format("Task was finished with result = %d", i);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        super.onProgressUpdate(values);
        String text = name + String.format(" -> doInBackground: %d", value);
        //Set text to TextView
        textView.setText(text);
        Log.d(TAG, text);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: Start");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: End");
    }
}
