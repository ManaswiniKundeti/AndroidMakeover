package com.example.androidmakeoverapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.example.androidmakeoverapp.R;
import com.example.androidmakeoverapp.data.ImageAssets;


//will display custom android image consisting of head, body and legs
public class AndroidMakeoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_android_makeover);

        //create new fragments only if there are no previously saved fragments
        if(savedInstanceState == null){

            BodyPartFragment headFragment = new BodyPartFragment();
            BodyPartFragment bodyFragment = new BodyPartFragment();
            BodyPartFragment legFragment = new BodyPartFragment();

            //add fragment to its container using fragment manager api
            FragmentManager fragmentManager = getSupportFragmentManager();

            //ADDING HEAD FRAGMENT
            // Set the list of image id's for the head fragment and
            // set the position to the second image in the list
            headFragment.setmImageIds(ImageAssets.getHeads());
            //headFragment.setmListIndex(1);
            // Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            headFragment.setmListIndex(headIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.head_fragment_container, headFragment)
                    .commit();

            //ADDING BODY FRAGMENT
            bodyFragment.setmImageIds(ImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            bodyFragment.setmListIndex(bodyIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.body_fragment_container, bodyFragment)
                    .commit();

            //ADDING LEG FRAGMENT
            legFragment.setmImageIds(ImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legIndex", 0);
            legFragment.setmListIndex(legIndex);
            fragmentManager.beginTransaction()
                    .add(R.id.leg_fragment_container, legFragment)
                    .commit();

        }
    }
}
