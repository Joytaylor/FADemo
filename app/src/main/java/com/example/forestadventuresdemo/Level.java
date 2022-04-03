package com.example.forestadventuresdemo;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Level extends AppCompatActivity {
    public static float screenRatioX, screenRatioY;
    private LevelView levelView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();

        getWindowManager().getDefaultDisplay().getSize(point);

        levelView = new LevelView(this, point.x, point.y);
        setContentView(levelView);


    }

    @Override
    protected void onPause() {
        super.onPause();
        levelView.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        levelView.resume();
    }

}
