package com.example.androidmakeoverapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.androidmakeoverapp.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //defining behaviour for image selected
    @Override
    public void onImageSelected(int position) {
        //create toast displaying position
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();
    }

}
