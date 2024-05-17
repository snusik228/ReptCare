package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

public class NameChoose extends AppCompatActivity {

    String this_difficulty; String reptile_name;
    EditText edit_text;
    TextView text_view;
    Button save_button; Button next_activity;
    MediaPlayer tap;
    MediaPlayer next;
    Toast toast;
    Intent i; Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_choose2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }

        next = MediaPlayer.create(NameChoose.this, R.raw.next);
        tap = MediaPlayer.create(NameChoose.this, R.raw.tap);

        i = getIntent();
        if (i!=null){
            this_difficulty = i.getStringExtra("send_text");
        }
        edit_text = (EditText) findViewById(R.id.editText);
        text_view = (TextView) findViewById(R.id.textView);
        save_button = (Button) findViewById(R.id.setName); next_activity = (Button) findViewById(R.id.next_activity);


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reptile_name = edit_text.getText().toString();
                if (reptile_name.equals(" ")) {
                    showAToast("Укажите корректное имя рептилии");
                    reptile_name = null;
                }
                else {
                    new Thread() {
                        public void run() {
                            if (tap.isPlaying() == false) {
                                tap.start();
                                text_view.setText("Имя: " + reptile_name);
                            }
                        }
                    }.start();
                }
            }
        });
        next_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.start();
                startHelloActivity();
            }
        });



    }
    private void startHelloActivity() {
        if (reptile_name != null) {
            intent = new Intent(NameChoose.this, Hello.class);
            intent.putExtra("this_difficulty", this_difficulty);
            intent.putExtra("name", reptile_name);
            startActivity(intent);
            finish();
        }
        else showAToast("Укажите имя рептилии для продолжения");
    }

    public void showAToast (String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}