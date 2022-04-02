package com.example.forestadventuresdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Start).setOnClickListener((view -> {
            startActivity(new Intent(Menu.this, LevelSelect.class ));
        }));
    }

}