package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    private GestureDetectorCompat mDetector;
    Button cont;
    private static final int PICK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cont = findViewById(R.id.buttonCont);
        cont.setOnClickListener((View.OnClickListener) MainActivity2.this);

        MainActivity2.MyGestureListener gestureListener = new MainActivity2.MyGestureListener();
        mDetector = new GestureDetectorCompat(this, gestureListener);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonCont) {
            pickContact();
        }
    }
    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            if (event2.getX() - event1.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                // Свайп вправо
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            } else if (event1.getX() - event2.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                // Свайп влево
                // Действия при необходимости
            }
            return super.onFling(event1, event2, velocityX, velocityY);
        }
    }
}