package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "Swipe Position";
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;
    ViewPager2 viewPager2;
    ArrayList<videomodel> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gestureDetector = new GestureDetector(MainActivity.this, this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager2 = (ViewPager2)findViewById(R.id.viewpager);
        videos = new ArrayList<>();



        videomodel ob1 = new videomodel(  "android.resource://"+getPackageName()+"/"+R.raw.v1 , "Video 1", "desc 1");
        videos.add(ob1);

        videomodel ob2 = new videomodel("android.resource://"+getPackageName()+"/"+R.raw.v2, "Video 2", "desc 2");
        videos.add(ob2);

        videomodel ob3 = new videomodel("android.resource://"+getPackageName()+"/"+R.raw.v3, "Video 3", "desc 3");
        videos.add(ob3);

        videomodel ob4 = new videomodel("android.resource://"+getPackageName()+"/"+R.raw.v4, "Video 4", "desc 4");
        videos.add(ob4);

        videomodel ob5 = new videomodel("android.resource://"+getPackageName()+"/"+R.raw.v5, "Video 5", "desc 5");
        videos.add(ob5);

        viewPager2.setAdapter(new videoadapter(videos));


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            //starting to swipe time gesture
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
             //ending time swipe gesture
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                //getting value for horizontal swipe
                float valueX = x2 - x1;
                //getting value for vertical swipe
                float valueY = y2 - y1;

                if(Math.abs(valueX) > MIN_DISTANCE) {
                    //detect left to right swipe
                    if(x2>x1) {
                        Toast.makeText(this, "Right is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Right Swipe");
                    }
                    else {
                        Toast.makeText(this, "Left is swiped", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Left swipe");
                    }
                }
                else if(Math.abs(valueY) > MIN_DISTANCE) {
                    //detects top to bottom swipe
                    if (y2 > y1) {
                        Toast.makeText(this, "Bottom swipe", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Bottom Swipe");
                    }
                    else {
                        Toast.makeText(this, "Top swipe", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Top swipe");

                    }
                }
        }


      return super.onTouchEvent(event);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}