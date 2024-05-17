package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
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
        text = (TextView)findViewById(R.id.death);

        text.setText("Death Activity");



    }
}