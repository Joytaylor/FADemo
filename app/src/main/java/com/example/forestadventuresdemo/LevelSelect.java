package com.example.forestadventuresdemo;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelSelect extends AppCompatActivity {
    public class LevelButton extends SurfaceView implements Runnable {

        private int screenX, screenY;
        Background b ;
        Paint paint;
        public LevelButton(Context context, int screenX, int screenY) {

            super(context);
            this.screenX =screenX;
            this.screenY =screenY;

            b =new Background(screenX, screenY, getResources(), 1);
            paint = new Paint();
        }
        @Override
        public void run() {
            draw();
        }
        public void draw(){
            int totalLevels = 1; // can replace with a level count

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        LevelButton levelSetUp = new LevelButton(this, point.x, point.y );
         setContentView(levelSetUp); // intialize View here

    }
}
