package com.example.forestadventuresdemo;

import static com.example.forestadventuresdemo.LevelView.screenRatioX;
import static com.example.forestadventuresdemo.LevelView.screenRatioY;
import static com.example.forestadventuresdemo.LevelView.screenY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Carrot {
    int x,y, width, height;
    int speed = 10;
    boolean collected =false;

    Bitmap carrot;
   public Carrot(Resources res) {
        carrot = BitmapFactory.decodeResource(res, R.drawable.carrot);
        width = carrot.getWidth() / 3;
        height = carrot.getWidth() / 3;
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        carrot = Bitmap.createScaledBitmap(carrot, width, height, false);


        y= -height;
    }
        Bitmap getCarrot(){
            return carrot;
        }
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }

}
