package com.example.forestadventuresdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    int x, y = 0;
    Bitmap background;
    Background (int screenX, int screenY, Resources res, int layer){
        if(layer == 1){
            background= BitmapFactory.decodeResource(res, R.drawable.clouds2);
        }
        else{
            background = BitmapFactory.decodeResource(res, R.drawable.clouds);
        }

        background = Bitmap.createScaledBitmap(background, screenX,screenY, false);
    }
}
