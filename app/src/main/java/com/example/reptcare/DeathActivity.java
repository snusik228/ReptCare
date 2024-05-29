package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DeathActivity extends AppCompatActivity {
    Intent i;
    private String death_reason;
    private String name;
    private long life_time;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.bar)); //status bar or the time bar at the top (see example image1)
        }
        i = getIntent();
        if (i!=null){
            name = i.getStringExtra("name");
            death_reason = i.getStringExtra("death_reason");
            life_time = i.getLongExtra("life_time", 1);
        }
        if (death_reason!=null) {
            ImageView sad_pic = (ImageView) findViewById(R.id.sad_pic);
            sad_pic.setImageAlpha(0);
            ImageView drought_pic = (ImageView) findViewById(R.id.drought_pic);
            drought_pic.setImageAlpha(0);
            ImageView thirst_pic = (ImageView) findViewById(R.id.thirst_pic);
            thirst_pic.setImageAlpha(0);
            ImageView cold_pic = (ImageView) findViewById(R.id.cold_pic);
            cold_pic.setImageAlpha(0);
            ImageView hot_pic = (ImageView) findViewById(R.id.hot_pic);
            hot_pic.setImageAlpha(0);
            ImageView satiety_pic = (ImageView) findViewById(R.id.satiety_pic);
            satiety_pic.setImageAlpha(0);

            text = (TextView) findViewById(R.id.death);
            text.setText("Конец игры! \n Сожалеем, но " + name + " скончался от " + death_reason + ". Время его(её) жизни составило " + life_time + " секунд реального времени. \n RIP");
            if (death_reason.equals("грусти")) sad_pic.setImageAlpha(255);
            else if (death_reason.equals("засухи")) drought_pic.setImageAlpha(255);
            else if (death_reason.equals("жажды")) thirst_pic.setImageAlpha(255);
            else if (death_reason.equals("холода")) cold_pic.setImageAlpha(255);
            else if (death_reason.equals("жары")) hot_pic.setImageAlpha(255);
            else if (death_reason.equals("голода")) satiety_pic.setImageAlpha(255);

        }
        else finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}