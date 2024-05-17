package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DifficultyChoose extends AppCompatActivity {
    String choosed_difficulty;
    Button hard_button;
    Button medium_button;
    Button easy_button;
    Button start_button;
    Intent i;
    MediaPlayer tap;
    MediaPlayer next;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //меняем цвет строки состояния если позволяет версия
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }


        //all texts and buttons
        TextView difficulty_choose = (TextView) findViewById(R.id.difficulty_choose);
        TextView easy_text = (TextView) findViewById(R.id.easy_level_description);

        hard_button = (Button) findViewById(R.id.hard);
        medium_button = (Button) findViewById(R.id.medium);
        easy_button = (Button) findViewById(R.id.easy);
        start_button = (Button) findViewById(R.id.start_button);
        next = MediaPlayer.create(DifficultyChoose.this, R.raw.next);
        tap = MediaPlayer.create(DifficultyChoose.this, R.raw.tap);


        //setting basement colors of buttons background
        difficulty_choose.setBackgroundColor(Color.argb(255,171, 255, 224));
        start_button.setBackgroundColor(Color.argb(255, 161, 161, 161));
        start_button.setTextColor(Color.argb(255,0,0,0));

        easy_text.setBackgroundColor(Color.argb(0, 165, 255, 135));
        easy_text.setTextColor(Color.argb(0, 0,0,0));


        //listening clicks and changing description texts
        hard_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    public void run() {
                        if (tap.isPlaying() == false) {
                            tap.start();
                            easy_text.setBackgroundColor(Color.argb(255, 250, 145, 145));
                            easy_text.setTextColor(Color.argb(255, 0,0,0));
                            easy_text.setText(R.string.hard_desc);

                            start_button.setBackgroundColor(Color.argb(255, 161, 161, 161));
                            start_button.setTextColor(Color.argb(255,0,0,0));

                            choosed_difficulty = "HARD";
                        }
                    }
                }.start();
            }
        });
        medium_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    public void run() {
                        if (tap.isPlaying() == false) {
                            tap.start();
                            easy_text.setBackgroundColor(Color.argb(255, 255, 206, 82));
                            easy_text.setTextColor(Color.argb(255, 0,0,0));
                            easy_text.setText(R.string.medium_desc);

                            start_button.setBackgroundColor(Color.argb(255, 161, 161, 161));
                            start_button.setTextColor(Color.argb(255,0,0,0));

                            choosed_difficulty = "MEDIUM";
                        }
                    }
                }.start();
            }
        });
        easy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    public void run() {
                        if (tap.isPlaying() == false) {
                            tap.start();
                            easy_text.setBackgroundColor(Color.argb(255, 165, 255, 135));
                            easy_text.setTextColor(Color.argb(255, 0,0,0));
                            easy_text.setText(R.string.easy_desc);

                            start_button.setBackgroundColor(Color.argb(255, 161, 161, 161));
                            start_button.setTextColor(Color.argb(255,0,0,0));

                            choosed_difficulty = "EASY";
                        }
                    }
                }.start();
            }
        });

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run() {
                        if (next.isPlaying() == false) {
                            next.start();
                        }
                    }
                }.start();
                startNewActivity();
            }
        });

    }

    public void startNewActivity() {
        if (choosed_difficulty != null) {
            i = new Intent(DifficultyChoose.this, NameChoose.class);
            i.putExtra("send_text", choosed_difficulty);
            startActivity(i);
            finish();
        }
        else showAToast("Выберите сложность для продолжения");

    }

    public void showAToast (String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}