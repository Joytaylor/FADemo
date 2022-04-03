package com.example.forestadventuresdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceView;

public class LevelView extends SurfaceView implements Runnable {
    private Thread thread;
    private Boolean isPlaying;
    private int screenX, screenY;
    private float screenRatiox, screenRatioY;
    private Background background1, background2;
    private Paint paint;



    public LevelView(Context context, int screenX, int screenY) {
        super(context);
        //setBackgroundColor(Color.rgb(148,255,255));
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatiox = 1920f /screenX;
        screenRatioY = 1000f/screenY;
        background1 = new Background(screenX, screenY, getResources(),1);
        background2 = new Background(screenX, screenY, getResources(), 2);
        background1.x -=500;
        background2.x = background1.background.getWidth()-1500;

        paint = new Paint();


    }

    @Override
    public void run() {

        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }
    public void draw(){

        background1.x -=15 * screenRatiox;
        background2.x -=12 * screenRatiox;


        if (background1.x +background1.background.getWidth() <0){
            background1.x = screenX;
        }
        if (background2.x +background2.background.getWidth() <0){
            background2.x = screenX;
        }

    }
    public void update(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
          //  getHolder().unlockCanvasAndPost(canvas);
            //canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }
    public void sleep(){
        try {
            Thread.sleep(25);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void resume(){
        isPlaying = true;
        thread=new Thread(this);
        thread.start();

    }
    public void pause(){
        isPlaying = false;
        try {
            thread.join();
        }catch ( InterruptedException e){
            e.printStackTrace();
        }

    }
}
