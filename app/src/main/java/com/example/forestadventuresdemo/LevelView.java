package com.example.forestadventuresdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;

public class LevelView extends SurfaceView implements Runnable {
    private Thread thread;
    private Boolean isPlaying;
    public static int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Background background1, background2, background3;
    private Paint paint;
    private Bunny bunny;
    //private Thread t;


    public LevelView(Context context, int screenX, int screenY) {
        super(context);
        //setBackgroundColor(Color.rgb(148,255,255));
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f /screenX;
        screenRatioY = 1000f/screenY;
        background1 = new Background(screenX, screenY, getResources(),1);
        background2 = new Background(screenX, screenY, getResources(), 2);
        background3 = new Background(screenX, screenY, getResources(),2);
        bunny = new Bunny(screenX, getResources());

      /*  t = new Thread(new Runnable() {
            @Override
            public void run() {
                bunny.setDebounce();
            }
        });*/
        background1.x -=500;
        background2.x = background1.background.getWidth()-1500;
        background3.x = background2.x + background2.background.getWidth();

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
    public void update(){

        background1.x -=10 * screenRatioX;
        background2.x -=10 * screenRatioX;
        background3.x -=10 *screenRatioX;
        int width = background2.background.getWidth();
        if (background1.x +background1.background.getWidth() <0){
            background1.x = background3.x+ width;
            //background2.x =0;
        }
        if (background2.x +background2.background.getWidth() <0){
            background2.x = background1.x+ width;
           // background1.x =0;
        }
        if (background3.x +background3.background.getWidth() <0){
            background3.x = background2.x+ width;
            // background1.x =0;
        }
        if (bunny.move != 0 ){
            bunny.setDebounce();
            if (bunny.move == -1){
                bunny.x -= (int)(screenRatioX *50);
            }
            else{
                bunny.x += (int)(screenRatioX *50);
            }
            if (bunny.x <0){
                bunny.x = 0;
            }
            else if(bunny.x>screenX - bunny.width){
                bunny.x = screenX -(bunny.width);
            }

        }
        if (bunny.stepCount ==5){
            bunny.move= 0;
            bunny.stepCount =0;
        }


    }
    public void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            canvas.drawBitmap(background3.background, background3.x, background3.y, paint);
            canvas.drawBitmap(bunny.getBunny(), bunny.x, bunny.y, paint);
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
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX()<bunny.x){
                    bunny.move = -1;
                }else{
                    bunny.move = 1;
                }
                break;
            case MotionEvent.ACTION_UP:
                ///currently doesnt host action
                break;
        }
        return true;
    }
}
