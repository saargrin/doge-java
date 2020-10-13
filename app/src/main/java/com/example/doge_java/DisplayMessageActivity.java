package com.example.doge_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DisplayMessageActivity extends AppCompatActivity {
    public String sendGetRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        try {
            String responseBody = response.body().string();
            return responseBody;
        } finally {
            response.close();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        String url ="http://xkcd.com/info.0.json";
        String result;
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String p_x = intent.getStringExtra(MainActivity.TB_1);
        String p_y = intent.getStringExtra(MainActivity.TB_2);
        String p_z = intent.getStringExtra(MainActivity.TB_3);
        try {
            result = sendGetRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            result = e.toString();
        }
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setHeight(200);
        textView.setWidth(300);
        textView.setTop(100);
        textView.setText(result);
        setContentView(textView);

    }
}
