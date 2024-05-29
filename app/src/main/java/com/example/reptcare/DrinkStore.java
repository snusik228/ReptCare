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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkStore extends AppCompatActivity {
    Reptile reptile = new Reptile();
    ImageButton water;
    ImageButton cockroach_tincture;
    ImageButton worm_coctail;
    ImageButton cricket_compote;
    ImageButton mosquito_juice;
    ImageButton exit;
    ImageView blue;

    MediaPlayer drink_sound;
    TextView drink_counter;
    TextView coins;
    Intent get_back;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_store);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }

        runTimer();
    }

    private void runTimer() {
        water = (android.widget.ImageButton) findViewById(R.id.water);
        mosquito_juice = (android.widget.ImageButton) findViewById(R.id.mosquito_juice);
        cockroach_tincture = (android.widget.ImageButton) findViewById(R.id.cockroach_tincture);
        worm_coctail = (android.widget.ImageButton) findViewById(R.id.worm_coctail);
        cricket_compote = (android.widget.ImageButton) findViewById(R.id.cricket_compote);
        exit = (android.widget.ImageButton) findViewById(R.id.exitButton);
        blue = (ImageView) findViewById(R.id.blue);
        drink_counter = (TextView) findViewById(R.id.drink_count);
        coins = (TextView) findViewById(R.id.coins);
        drink_sound = MediaPlayer.create(DrinkStore.this, R.raw.drink_sound);


        setStats();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkWater();
            }
        });

        mosquito_juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkJuice();
            }
        });


        cockroach_tincture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkTincture();
            }
        });

        worm_coctail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkCoctail();

            }
        });

        cricket_compote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkCompote();
            }
        });

        final Handler handler_1 = new Handler();
        handler_1.post(new Runnable() {
            @Override
            public void run()
            {
                setStats();
                handler_1.postDelayed(this, 100);
            }
        });
    }
    public void drinkWater() {
        if (reptile.coins - 30 >= 0 && 1000 - reptile.getThirst_level() >= 100) {
            new Thread(){
                public void run() {
                    if (drink_sound.isPlaying() == false && reptile.coins - 30 >= 0 && 1000 - reptile.getThirst_level() >= 100) {
                        drink_sound.start();
                        reptile.coins -= 30;
                        reptile.setThirst_level(reptile.getThirst_level() + 100);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 30 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getThirst_level() < 100) showAToast(reptile.getName() + " достаточно напился(сь)");
    }

    public void drinkJuice() {
        if (reptile.coins - 70 >= 0 && 1000 - reptile.getThirst_level() >= 200) {
            new Thread(){
                public void run() {
                    if (drink_sound.isPlaying() == false && reptile.coins - 70 >= 0 && 1000 - reptile.getThirst_level() >= 200) {
                        drink_sound.start();
                        reptile.coins -= 70;
                        reptile.setThirst_level(reptile.getThirst_level() + 200);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 70 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getThirst_level() < 200) showAToast(reptile.getName() + " достаточно напился(сь)");

    }

    public void drinkTincture() {
        if (reptile.coins - 100 >= 0 && 1000 - reptile.getThirst_level() >= 400) {
            new Thread(){
                public void run() {
                    if (drink_sound.isPlaying() == false && reptile.coins - 100 >= 0 && 1000 - reptile.getThirst_level() >= 400) {
                        drink_sound.start();
                        reptile.coins -= 100;
                        reptile.setThirst_level(reptile.getThirst_level() + 400);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 100 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getThirst_level() < 400) showAToast(reptile.getName() + " достаточно напился(сь)");
    }

    public void drinkCoctail() {
        if (reptile.coins - 300 >= 0 && 1000 - reptile.getThirst_level() >= 500) {
            new Thread(){
                public void run() {
                    if (drink_sound.isPlaying() == false && reptile.coins - 300 >= 0 && 1000 - reptile.getThirst_level() >= 500) {
                        drink_sound.start();
                        reptile.coins -= 300;
                        reptile.setThirst_level(reptile.getThirst_level() + 500);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 300 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getThirst_level() < 500) showAToast(reptile.getName() + " достаточно напился(сь)");
    }

    public void drinkCompote() {
        if (reptile.coins - 500 >= 0 && 1000 - reptile.getThirst_level() >= 700) {
            new Thread(){
                public void run() {
                    if (drink_sound.isPlaying() == false && reptile.coins - 500 >= 0 && 1000 - reptile.getThirst_level() >= 700) {
                        drink_sound.start();
                        reptile.coins -= 500;
                        reptile.setThirst_level(reptile.getThirst_level() + 700);
                        setStats();
                    }
                }
            }.start();
        }
        else if (reptile.coins - 500 < 0) showAToast("Недостаточно денег для покупки");
        else if (1000 - reptile.getThirst_level() < 700) showAToast(reptile.getName() + " достаточно напился(сь)");
    }

    public void setStats(){
        blue.setImageAlpha(map(reptile.getThirst_level(), 0, 1000, 0, 255));
        drink_counter.setText(Integer.toString(reptile.getThirst_level() / 10) + "%");
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
