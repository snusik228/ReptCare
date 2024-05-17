package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EatStore extends AppCompatActivity {
    Reptile reptile = new Reptile();
    ImageButton caterpillar;
    ImageButton cricket;
    ImageButton beetle;
    ImageButton larva;
    ImageButton cockroach;
    ImageButton exit;
    ImageView green;
    MediaPlayer eat_sound;
    TextView satiety_counter;
    TextView coins;
    Intent get_back;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_store);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }

        runTimer();
    }

    private void runTimer() {
        caterpillar = (android.widget.ImageButton) findViewById(R.id.caterpillar_button);
        beetle = (android.widget.ImageButton) findViewById(R.id.beetle_button);
        larva = (android.widget.ImageButton) findViewById(R.id.larva_button);
        cockroach = (android.widget.ImageButton) findViewById(R.id.cockroach_button);
        cricket = (android.widget.ImageButton) findViewById(R.id.cricket_button);
        exit = (android.widget.ImageButton) findViewById(R.id.exitButton);
        green = (ImageView) findViewById(R.id.green);
        satiety_counter = (TextView) findViewById(R.id.eat_count);
        coins = (TextView) findViewById(R.id.coins);
        eat_sound = MediaPlayer.create(EatStore.this, R.raw.eat_sound);


        setStats();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        caterpillar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatCaterpillar();
            }
        });

        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatCricket();
            }
        });


        beetle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatBeetle();
            }
        });

        larva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatLarva();

            }
        });

        cockroach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatCockroach();
            }
        });

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                setStats();
                handler.postDelayed(this, 100);
            }
        });
    }
    public void eatCaterpillar() {
        if (reptile.coins - 100 >= 0 && 1000 - reptile.getSatiety_level() >= 100) {
            new Thread(){
                public void run() {
                    if (eat_sound.isPlaying() == false && reptile.coins - 100 >= 0 && 1000 - reptile.getSatiety_level() >= 100) {
                        eat_sound.start();
                        reptile.coins -= 100;
                        reptile.setSatiety_level(reptile.getSatiety_level() + 100);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 100 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getSatiety_level() < 100) showAToast(reptile.getName() + " достаточно сыт(а)");

    }

    public void eatCricket() {
        if (reptile.coins - 300 >= 0 && 1000 - reptile.getSatiety_level() >= 200) {
            new Thread(){
                public void run() {
                    if (eat_sound.isPlaying() == false && reptile.coins - 300 >= 0 && 1000 - reptile.getSatiety_level() >= 200) {
                        eat_sound.start();
                        reptile.coins -= 300;
                        reptile.setSatiety_level(reptile.getSatiety_level() + 200);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 300 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getSatiety_level() < 200) showAToast(reptile.getName() + " достаточно сыт(а)");

    }

    public void eatBeetle() {
        if (reptile.coins - 600 >= 0 && 1000 - reptile.getSatiety_level() >= 400) {
            new Thread(){
                public void run() {
                    if (eat_sound.isPlaying() == false && reptile.coins - 600 >= 0 && 1000 - reptile.getSatiety_level() >= 400) {
                        eat_sound.start();
                        reptile.coins -= 600;
                        reptile.setSatiety_level(reptile.getSatiety_level() + 400);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 600 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getSatiety_level() < 400) showAToast(reptile.getName() + " достаточно сыт(а)");

    }

    public void eatLarva() {
        if (reptile.coins - 700 >= 0 && 1000 - reptile.getSatiety_level() >= 500) {
            new Thread(){
                public void run() {
                    if (eat_sound.isPlaying() == false && reptile.coins - 700 >= 0 && 1000 - reptile.getSatiety_level() >= 500) {
                        eat_sound.start();
                        reptile.coins -= 700;
                        reptile.setSatiety_level(reptile.getSatiety_level() + 500);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 700 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getSatiety_level() < 500) showAToast(reptile.getName() + " достаточно сыт(а)");
    }

    public void eatCockroach() {
        if (reptile.coins - 1000 >= 0 && 1000 - reptile.getSatiety_level() >= 700) {
            new Thread(){
                public void run() {
                    if (eat_sound.isPlaying() == false && reptile.coins - 1000 >= 0 && 1000 - reptile.getSatiety_level() >= 700) {
                        eat_sound.start();
                        reptile.coins -= 1000;
                        reptile.setSatiety_level(reptile.getSatiety_level() + 700);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 1000 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getSatiety_level() < 700) showAToast(reptile.getName() + " достаточно сыт(а)");

    }

    public void setStats(){
        green.setImageAlpha(map(reptile.getSatiety_level(), 0, 1000, 0, 255));
        satiety_counter.setText(Integer.toString(reptile.getSatiety_level() / 10) + "%");
        coins.setText(Integer.toString(reptile.coins));
    }

    public int map(int x, int in_min, int in_max, int out_min, int out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public void showAToast (String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


}



