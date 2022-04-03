package com.example.forestadventuresdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Vector;
import static com.example.forestadventuresdemo.LevelView.screenRatioX;
import static com.example.forestadventuresdemo.LevelView.screenRatioY;
public class Player {
    public int speed = 10;
    int x, y, width, height = 0;
    Bitmap rabbit;
    Player(Resources res){
        rabbit = BitmapFactory.decodeResource(res, R.drawable.bunnybasesprite);

        width = rabbit.getWidth();
        height = rabbit.getHeight();

        width /= 3;
        height /=3;

        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);
        rabbit = Bitmap.createScaledBitmap(rabbit, width, height, false);
        y = -height;
        x =-width;
    }
    Rect getCollisionShape () {
        return new Rect(x, y, x + width, y + height);
    }
}
