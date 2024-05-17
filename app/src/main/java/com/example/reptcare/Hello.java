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
import android.widget.TextView;
import android.widget.Toast;

public class Hello extends AppCompatActivity {
    public int text_counter = 1;
    String this_difficulty;
    String reptile_name;
    Intent intent;
    MediaPlayer tap;
    MediaPlayer next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }

        next = MediaPlayer.create(Hello.this, R.raw.next);
        tap = MediaPlayer.create(Hello.this, R.raw.tap);

        intent = getIntent();
        if (intent!=null){
            this_difficulty = intent.getStringExtra("this_difficulty");
            reptile_name = intent.getStringExtra("name");
        }

        Button next = (Button) findViewById(R.id.next);
        Button back = (Button) findViewById(R.id.back);
        Button skip = (Button) findViewById(R.id.skip);
        TextView text = (TextView)findViewById(R.id.hello_textView);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run() {
                        if (tap.isPlaying() == false) {
                            tap.start();

                            text_counter+=1;
                            if (text_counter == 1){
                                text.setText(R.string.first);
                            }
                            else if (text_counter == 2){
                                text.setText(R.string.second);
                            }
                            else if (text_counter == 3){
                                text.setText(R.string.third);
                            }
                            else if (text_counter == 4){
                                text.setText(R.string.fourth);
                            }
                            else if (text_counter == 5){
                                text.setText(R.string.fifth);
                            }
                            else if (text_counter > 5) {
                                startMainGame();
                            }
                        }
                    }
                }.start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run() {
                        if (tap.isPlaying() == false) {
                            tap.start();

                            text_counter-=1;
                            if (text_counter == 1){
                                text.setText(R.string.first);
                            }
                            else if (text_counter == 2){
                                text.setText(R.string.second);
                            }
                            else if (text_counter == 3){
                                text.setText(R.string.third);
                            }
                            else if (text_counter == 4){
                                text.setText(R.string.fourth);
                            }
                            else if (text_counter == 5){
                                text.setText(R.string.fifth);
                            }
                            else if (text_counter < 1) text_counter = 1;

                        }
                    }
                }.start();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainGame();
            }
        });

    }
    private void startMainGame() {
        new Thread(){
            public void run() {
                tap.stop();
                next.start();
                intent = new Intent(Hello.this, MainGame.class);
                intent.putExtra("this_difficulty", this_difficulty);
                intent.putExtra("name", reptile_name);
                startActivity(intent);
                finish();
            }
        }.start();


    }

}