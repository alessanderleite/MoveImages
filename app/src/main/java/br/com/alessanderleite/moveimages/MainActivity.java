package br.com.alessanderleite.moveimages;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Screen Size
    private int screenWidth;
    private int screenHeight;

    //Images
    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowRight;
    private ImageView arrowLeft;

    //Position
    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRightX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;

    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrowUp = (ImageView) findViewById(R.id.arrowUp);
        arrowDown = (ImageView) findViewById(R.id.arrowDown);
        arrowRight = (ImageView) findViewById(R.id.arrowRight);
        arrowLeft = (ImageView) findViewById(R.id.arrowLeft);

        //Get Screen Size.
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Move to out of screen.
        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);

        //Start the timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);

    }

    public void changePos() {
        //Up
        arrowUpY -= 10;
        if (arrowUp.getY() + arrowUp.getHeight() < 0) {
            arrowUpX = (float)Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);
    }
}
