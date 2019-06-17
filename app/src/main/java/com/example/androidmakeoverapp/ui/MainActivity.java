package com.example.androidmakeoverapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.androidmakeoverapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //defining behaviour for image selected
    public void onImageSelected(int position) {
        //create toast displaying position
    }
}
