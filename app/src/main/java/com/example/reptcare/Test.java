package com.example.reptcare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Test extends AppCompatActivity {
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button button = (Button) findViewById(R.id.button);
        ImageView reptile = (ImageView) findViewById(R.id.reptile_base);
        ImageView sad = (ImageView) findViewById(R.id.sad);
        sad.setImageAlpha(0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    sad.setImageAlpha(255);
                    flag = !flag;
                }
                else {
                    sad.setImageAlpha(0);
                    flag = !flag;
                }
            }
        });
    }
}