package com.example.doge_java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String TB_1= "com.example.doge_java.MESSAGE";
    public static final String TB_2= "com.example.doge_java.MESSAGE";
    public static final String TB_3= "com.example.doge_java.MESSAGE";


    private void send_message() {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText text1b = (EditText) findViewById(R.id.textbox1);
//        EditText text2b = (EditText) findViewById(R.id.textbox2);
//        EditText text3b = (EditText) findViewById(R.id.textbox3);
        String text1 = text1b.getText().toString();
//        String text2 = text2b.getText().toString();
//        String text3 = text3b.getText().toString();
        intent.putExtra(TB_1, text1);
//        intent.putExtra(TB_2, text2);
//        intent.putExtra(TB_3, text3);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button but1 = findViewById(R.id.button1);
        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),text1,Toast.LENGTH_SHORT).show();
                send_message();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
