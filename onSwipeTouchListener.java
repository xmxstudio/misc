package com.xmxstudio.cashonhand;

/**
 * Created by xmetrix on 12/28/2014.
 */

import android.util.Log;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;

/**
 * Detects left and right swipes across a view.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private View swipeView;

    public OnSwipeTouchListener(Context context, View v) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        swipeView = v;

    }
    public void onSwipeLeft(View v) {
        Log.v("swipe","left");
    }
    public void onSwipeRight(View v) {
        Log.v("swipe","right");
    }
    public void onSwipeUp(View v){
        Log.v("swipe","up");
    }
    public void onSwipeDown(View v){
     Log.v("swipe","down");
    }
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int swipeDistanceThreshold = 100;
        private static final int swipeVelocityThreshold = 100;
        private static final int swipeVertVelocityThreshold =1000;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.v("VELOCITYY",String.valueOf(velocityY));
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > swipeDistanceThreshold && Math.abs(velocityX) > swipeVelocityThreshold) {
                        if (diffX > 0) {
                            onSwipeRight(swipeView);
                        } else {
                            onSwipeLeft(swipeView);
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > swipeDistanceThreshold && Math.abs(velocityY) > swipeVertVelocityThreshold) {
                    if (diffY > 0) {
                        onSwipeDown(swipeView);
                    } else {
                        onSwipeUp(swipeView);
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }

    }
}


