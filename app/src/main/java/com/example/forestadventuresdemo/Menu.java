package com.example.forestadventuresdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findViewById(R.id.Start).setOnClickListener((view -> {
            startActivity(new Intent(Menu.this, Level.class ));
        }));
        TextView highScore = findViewById(R.id.highScore);
        SharedPreferences preferences = getSharedPreferences("game", MODE_PRIVATE);
        highScore.setText("HighScore: "+preferences.getInt("highScore", 0));
    }

}