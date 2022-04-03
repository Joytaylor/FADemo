package com.example.forestadventuresdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;

import java.util.Random;

public class LevelView extends SurfaceView implements Runnable {
    private Thread thread;
    private Boolean isPlaying, gameOver;
    private Random rand;
    public static int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Background background1, background2, background3;
    private Paint paint;
    private Bunny bunny;
    private Carrot[] carrots; //replace with enemies
    //private Thread t;
    private  int score;
    private SharedPreferences prefs;
    private Level activity;


    public LevelView(Level context, int screenX, int screenY) {
        super(context);

        this.activity = context;

        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);
        score =0;
        rand = new Random();
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

        carrots = new Carrot[3];
        for (int i =0; i< carrots.length; i++){
            carrots[i] = new Carrot(getResources());
        }
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.BLACK);
        gameOver = false;

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

    for (Carrot carrot :carrots){

        if (carrot.y + carrot.height <= 0){
             int bound = (int) (25 * screenRatioY);
             carrot.speed = rand.nextInt(bound)+2;
            carrot.x = rand.nextInt(screenX - carrot.width);
            carrot.y = rand.nextInt(carrot.height+ 100)*-1;
            carrot.collected =false;
        }
        carrot.y += carrot.speed;
        if (Rect.intersects(carrot.getCollisionShape(), bunny.getCollisionShape())){
            score++;
            carrot.y = (carrot.height+ rand.nextInt(100)) *-1;
            carrot.collected = true;
        }
        if (carrot.y> screenY - carrot.height/2){
            gameOver = true;
            return;
        }
    }

    }
    public void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);
            canvas.drawBitmap(background3.background, background3.x, background3.y, paint);
            canvas.drawBitmap(bunny.getBunny(), bunny.x, bunny.y, paint);
            canvas.drawText(Integer.toString(score), screenX/2f, screenY/4f, paint);
            for(Carrot carrot: carrots){
                canvas.drawBitmap(carrot.getCarrot(), carrot.x, carrot.y, paint);

            }
            if (gameOver){
                checkHighScore();
                waitThenExit();

            }
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
    public void checkHighScore(){
        if (prefs.getInt("highScore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", score);
            editor.apply();
        }
    }
    public void waitThenExit(){
        try {
            Thread.sleep(4000);
            activity.startActivity(new Intent(activity, Menu.class));
            activity.finish();
        } catch (InterruptedException e) {
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
                bunny.stepCount = 0;
                break;
            case MotionEvent.ACTION_UP:
                ///currently doesnt host action
                break;
        }
        return true;
    }
}
