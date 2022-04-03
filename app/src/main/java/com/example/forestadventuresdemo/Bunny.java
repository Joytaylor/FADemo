package com.example.forestadventuresdemo;

import static com.example.forestadventuresdemo.LevelView.screenRatioX;
import static com.example.forestadventuresdemo.LevelView.screenRatioY;
import static com.example.forestadventuresdemo.LevelView.screenY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bunny {
    int x, y, width , height;
    int move, stepCount = 0;
    boolean debounce = false;
    Bitmap bunny;
    public Bunny( int screenX, Resources res){
        bunny = BitmapFactory.decodeResource(res, R.drawable.bunnybasesprite);

        width = bunny.getWidth();
        height = bunny.getHeight();

        width /= 2;
        height /= 2;
        width = (int) (width *screenRatioX);
        height = (int) (height *screenRatioY);

        bunny = Bitmap.createScaledBitmap(bunny, width, height, false);
        x = screenX/2;
        y = (int) (-height* screenRatioY)+screenY;

    }
    Bitmap getBunny(){
        return bunny; // change sprite in this functions
    }
    public void setDebounce(){
        stepCount++;
    }
}
