package com.example.reptcare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.NotificationManager;

import java.util.Locale;

public class MainGame extends AppCompatActivity {
    private long mseconds = 0;
    private boolean lamp_flag = false;
    private String death_reason;
    Intent intent;
    Intent intent_2;
    Intent intent_3;
    String this_difficulty;
    String reptile_name;
    Toast toast;
    MediaPlayer spray_sound;
    MediaPlayer lamp_on;
    MediaPlayer lamp_off;
    MediaPlayer lamp_sound;
    MediaPlayer drink_sound;
    MediaPlayer eat_sound;
    MediaPlayer playing_sound;
    TextView timeView;
    TextView happy_stats;
    TextView eat_stats;
    TextView drink_stats;
    TextView temp_stats;
    TextView hum_stats;
    TextView coin_counter;
    Runnable runnable;
    public Handler handler;

    public Reptile my_reptile = new Reptile();
    final int happy_delta = 50;
    final int coin_delta = 500;
    final int moisture_delta = 100;
    private boolean temp_notify_flag = false;
    private boolean happy_notify_flag = false;
    private boolean satiety_notify_flag = false;
    private boolean thirst_notify_flag = false;
    private boolean moisture_notify_flag = false;

    private NotificationManager notificationManager;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }
        startService(new Intent(this, MyService.class));

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        intent = getIntent();
        if (intent!=null){
            this_difficulty = intent.getStringExtra("this_difficulty");
            reptile_name = intent.getStringExtra("name");
        }
        my_reptile.setName(reptile_name);
        my_reptile.setDifficulty(this_difficulty);
        runTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        notificationManager.cancelAll();
    }
    //    @Override
//    protected void onSaveInstanceState(Bundle state) {
//        state.putInt("happy", my_reptile.getHappy_level());
//        state.putInt("satiety", my_reptile.getSatiety_level());
//        state.putInt("thirst", my_reptile.getThirst_level());
//        state.putInt("temperature", my_reptile.getTemperature_level());
//        state.putInt("moisture", my_reptile.getMoisture_level());
//        state.putInt("coins", my_reptile.coins);
//        super.onSaveInstanceState(state);
//    }


    private void runTimer()
    {
//        timeView = (TextView)findViewById(R.id.stats);
        happy_stats = (TextView)findViewById(R.id.happy_stats);
        eat_stats = (TextView)findViewById(R.id.eat_stats);
        drink_stats = (TextView)findViewById(R.id.drink_stats);
        temp_stats = (TextView)findViewById(R.id.temp_stats);
        hum_stats = (TextView)findViewById(R.id.hum_stats);
        coin_counter = (TextView)findViewById(R.id.coin_counter);
        coin_counter.setText(Integer.toString(my_reptile.coins));
        //icons
        ImageView light = (ImageView)findViewById(R.id.light);
        ImageView happy = (ImageView)findViewById(R.id.yellow);
        ImageView eat = (ImageView)findViewById(R.id.green);
        ImageView drink = (ImageView)findViewById(R.id.blue);
        ImageView temp = (ImageView)findViewById(R.id.red);
        ImageView hum = (ImageView)findViewById(R.id.d_blue);
        //masks
        ImageView sad_mask = (ImageView)findViewById(R.id.sad_mask); sad_mask.setImageAlpha(0);
        ImageView spray = (ImageView)findViewById(R.id.spray);
        ImageView overweight_mask = (ImageView)findViewById(R.id.overweight_mask); overweight_mask.setImageAlpha(0);
        ImageView lessweight_mask = (ImageView)findViewById(R.id.lessweight_mask); lessweight_mask.setImageAlpha(0);
        ImageView hot_mask = (ImageView)findViewById(R.id.hot_mask); hot_mask.setImageAlpha(0);
        ImageView cold_mask = (ImageView)findViewById(R.id.cold_mask); hot_mask.setImageAlpha(0);
        ImageView lug = (ImageView)findViewById(R.id.lug);
        ImageView tropic = (ImageView)findViewById(R.id.tropic);
        ImageView desert = (ImageView)findViewById(R.id.desert);
        //buttons
        Button play_button = (Button)findViewById(R.id.play_button);
        Button lamp_button = (Button)findViewById(R.id.lamp_button);
        Button humidity_button = (Button)findViewById(R.id.humidity_button);
        Button eat_button = (Button)findViewById(R.id.eat_button);
        Button drink_button = (Button)findViewById(R.id.drink_button);
        //sounds
        spray_sound = MediaPlayer.create(MainGame.this, R.raw.spray);
        lamp_on = MediaPlayer.create(MainGame.this, R.raw.lamp_on);
        lamp_off = MediaPlayer.create(MainGame.this, R.raw.lamp_off);
        playing_sound = MediaPlayer.create(MainGame.this, R.raw.playing);


        light.setImageAlpha(0);


        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1000 - my_reptile.getHappy_level() < happy_delta){
                    overHappy();
                }
                else {
                    new Thread(){
                        public void run() {
                            if (playing_sound.isPlaying() == false) {
                                playing_sound.start();
                                my_reptile.coins += coin_delta;
                                my_reptile.setHappy_level(my_reptile.getHappy_level() + happy_delta);
                            }

                        }
                    }.start();
                }
            }
        });
        eat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eatStore();
            }
        });
        drink_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drinkStore();
            }
        });

        lamp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!lamp_flag) {
                    new Thread(){
                        public void run() {
                            if (lamp_on.isPlaying() == false) {
                                lamp_on.start();
                                light.setImageAlpha(255);
                                lamp_button.setText(R.string.lamp_off);
                                my_reptile.lamp = true;
                                lamp_flag = !lamp_flag;
                            }

                        }
                    }.start();
                }
                else {
                    new Thread(){
                        public void run() {
                            if (lamp_off.isPlaying() == false) {
                                lamp_off.start();
                                light.setImageAlpha(0);
                                lamp_button.setText(R.string.lamp_on);
                                my_reptile.lamp = false;
                                lamp_flag = !lamp_flag;
                            }

                        }
                    }.start();
                }
            }
        });

        spray.setImageAlpha(0);
        humidity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spray();
            }
        });

        setTextStats();

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                if (my_reptile.lamp == false) {
                    if (my_reptile.getDifficulty().equals("HARD")) {
                        if (mseconds % 100 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() - 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    } else if (my_reptile.getDifficulty().equals("MEDIUM")) {
                        if (mseconds % 1000 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() - 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    } else if (my_reptile.getDifficulty().equals("EASY")) {
                        if (mseconds % 2000 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() - 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    }
            }
                else {
                    if (my_reptile.getDifficulty().equals("HARD")) {
                        if (mseconds % 100 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() + 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    } else if (my_reptile.getDifficulty().equals("MEDIUM")) {
                        if (mseconds % 1000 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() + 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    } else if (my_reptile.getDifficulty().equals("EASY")) {
                        if (mseconds % 2000 == 0) {
                            my_reptile.setTemperature_level(my_reptile.getTemperature_level() + 1);
                            my_reptile.setThirst_level(my_reptile.getThirst_level() - 1);
                            my_reptile.setHappy_level(my_reptile.getHappy_level() - 1);
                            my_reptile.setSatiety_level(my_reptile.getSatiety_level() - 1);
                            my_reptile.setMoisture_level(my_reptile.getMoisture_level() - 1);
                        }
                    }
                }

                setTextStats();


                happy.setImageAlpha(map(my_reptile.getHappy_level(), 0,1000,0,255));
                eat.setImageAlpha(map(my_reptile.getSatiety_level(), 0,1000,0,255));
                drink.setImageAlpha(map(my_reptile.getThirst_level(), 0,1000,0,255));
                temp.setImageAlpha(map(my_reptile.getTemperature_level(), 0,1000,0,255));
                hum.setImageAlpha(map(my_reptile.getMoisture_level(), 0,1000,0,255));

                //temperature mask
                if (my_reptile.getTemperature_level() > 800) {
                    hot_mask.setImageAlpha(255);
                    cold_mask.setImageAlpha(0);
                    if (temp_notify_flag == false) {
                        doNotification(1, "Горячо!", my_reptile.getName() + " страдает от перегрева. Выключите лампу");
                        temp_notify_flag = true;
                    }
                }
                else if (my_reptile.getTemperature_level() <= 800 && my_reptile.getTemperature_level() > 300) {
                    hot_mask.setImageAlpha(0);
                    cold_mask.setImageAlpha(0);
                    temp_notify_flag = false;
                }
                else if (my_reptile.getTemperature_level() <= 300) {
                    hot_mask.setImageAlpha(0);
                    cold_mask.setImageAlpha(255);
                    if (temp_notify_flag == false) {
                        doNotification(1, "Брр... Холодно!", my_reptile.getName() + " страдает от переохлаждения. Включите лампу");
                        temp_notify_flag = true;
                    }
                }

                //happy mask
                if (my_reptile.getHappy_level() < 400){
                    sad_mask.setImageAlpha(255);
                    if (happy_notify_flag == false) {
                        doNotification(2, "Как-то скучно :(", my_reptile.getName() + " тоскует. Поиграйте с питомцем");
                        happy_notify_flag = true;
                    }
                }
                else {
                    sad_mask.setImageAlpha(0);
                    happy_notify_flag = false;
                }


                //weight mask
                if (my_reptile.getSatiety_level() > 800) {
                    overweight_mask.setImageAlpha(255);
                    lessweight_mask.setImageAlpha(0);
                    satiety_notify_flag = false;
                }
                else if (my_reptile.getSatiety_level() <= 800 && my_reptile.getSatiety_level() > 300) {
                    lessweight_mask.setImageAlpha(0);
                    overweight_mask.setImageAlpha(0);
                    satiety_notify_flag = false;
                }
                else if (my_reptile.getSatiety_level() <= 300) {
                    lessweight_mask.setImageAlpha(255);
                    overweight_mask.setImageAlpha(0);
                    if (satiety_notify_flag == false) {
                        doNotification(3, "Кожа да кости", my_reptile.getName() + " страдает от голода. Покормите питомца");
                        satiety_notify_flag = true;
                    }
                }
                //thirst mask
                if (my_reptile.getThirst_level() > 300) {
                    thirst_notify_flag = false;
                }
                else if (my_reptile.getThirst_level() <= 300) {
                    if (thirst_notify_flag == false) {
                        doNotification(4, "Сухость во рту", my_reptile.getName() + " страдает от жажды. Напоите питомца");
                        thirst_notify_flag = true;
                    }
                }
                //moisture mask
                if (my_reptile.getMoisture_level() < 300){
                    desert.setImageAlpha(70);
                    lug.setImageAlpha(0);
                    tropic.setImageAlpha(0);
                    if (moisture_notify_flag == false) {
                        doNotification(5, "Перекати поле", my_reptile.getName() + " страдает от сухости. Опрыскайте питомца");
                        moisture_notify_flag = true;
                    }
                }
                else if (my_reptile.getMoisture_level() >= 300 && my_reptile.getMoisture_level() <= 800){
                    desert.setImageAlpha(0);
                    lug.setImageAlpha(70);
                    tropic.setImageAlpha(0);
                }
                else if (my_reptile.getMoisture_level() > 800){
                    desert.setImageAlpha(0);
                    lug.setImageAlpha(0);
                    tropic.setImageAlpha(70);
                }
                cheakDeath();

                mseconds+=100;
                handler.postDelayed(this, 100);
            }
        });
    }

    public int map(int x, int in_min, int in_max, int out_min, int out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public void setTextStats() {
        coin_counter.setText(Integer.toString(my_reptile.coins));
        happy_stats.setText(Integer.toString(my_reptile.getHappy_level() / 10) + "%");
        eat_stats.setText(Integer.toString(my_reptile.getSatiety_level()/ 10) + "%");
        drink_stats.setText(Integer.toString(my_reptile.getThirst_level()/ 10) + "%");
        temp_stats.setText(Integer.toString(my_reptile.getTemperature_level()/ 10) + "%");
        hum_stats.setText(Integer.toString(my_reptile.getMoisture_level()/ 10) + "%");
    }
    private void overHappy() {
        showAToast(my_reptile.getName() + " достаточно счастлив(а)");
    }
    private void eatStore(){
        intent = new Intent(MainGame.this, EatStore.class);
        startActivity(intent);

    }
    private void drinkStore(){
        intent_2 = new Intent(MainGame.this, DrinkStore.class);
        startActivity(intent_2);
    }
    private void spray(){
        new Thread(){
            public void run() {
                if (spray_sound.isPlaying() == false) {
                    spray_sound.start();
                    my_reptile.setMoisture_level(my_reptile.getMoisture_level()+moisture_delta);
                }

            }
        }.start();
    }
    public void showAToast (String message){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void doNotification(int notify_id, String title, String text){
        Intent k = new Intent(this, MainGame.class);
        k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                        R.drawable.icon))
                        .setWhen(System.currentTimeMillis())
                        .setContentTitle(title)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentText(text);
        createChannelIfNeeded(notificationManager);
        notificationManager.notify(notify_id, notificationBuilder.build());
    }

    public static void createChannelIfNeeded(NotificationManager manager){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
    private void cheakDeath() {
        if (my_reptile.getHappy_level() == 0) {
            death_reason = "грусти";
            onStop();
        } else if (my_reptile.getThirst_level() == 0) {
            death_reason = "жажды";
            onStop();
        } else if (my_reptile.getSatiety_level() == 0) {
            death_reason = "голода";
            onStop();
        } else if (my_reptile.getTemperature_level() == 1000) {
            death_reason = "жары";
            onStop();
        } else if (my_reptile.getTemperature_level() == 0) {
            death_reason = "холода";
            onStop();
        } else if (my_reptile.getMoisture_level() == 0) {
            death_reason = "засухи";
            onStop();
        }
    }

    protected void onStop() {
        super.onStop();
        intent_3 = new Intent(MainGame.this, DeathActivity.class);
        intent_3.putExtra("death_reason", death_reason);
        intent_3.putExtra("name", reptile_name);
        intent_3.putExtra("life_time", mseconds / 1000);
        startActivity(intent_3);

    }

}




