package com.example.doge_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DisplayMessageActivity extends AppCompatActivity {
    public String sendGetRequest(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "http://xkcd.com/"+id+"/info.0.json";
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        try {
            String responseBody = response.body().string();
            return responseBody;
        } finally {
            response.close();
        }
    }

    public Bitmap DownloadImageFromPath(String path){
        InputStream in =null;
        Bitmap bmp=null;
        int responseCode = -1;
        try{

            URL url = new URL(path);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
            }

        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }
        return bmp;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        String result;
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra(MainActivity.TB_1);
//        String p_y = intent.getStringExtra(MainActivity.TB_2);
//        String p_z = intent.getStringExtra(MainActivity.TB_3);
        try {
            result = sendGetRequest(id);
            JSONObject jres = new JSONObject(result);
            result = jres.getString("img");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            result = e.toString();
        }
        Bitmap comic = DownloadImageFromPath(result);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setHeight(200);
        textView.setWidth(300);
        textView.setTop(100);
        textView.setText(result);
        setContentView(textView);
        ImageView imgView = new ImageView(this);
        imgView.setImageBitmap(comic);
        setContentView(imgView);

    }
}
