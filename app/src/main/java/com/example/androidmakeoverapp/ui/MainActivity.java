package com.example.androidmakeoverapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidmakeoverapp.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
    //var to store values for list index of the selected images
    //default value index=0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;


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

        // Based on where a user has clicked, store the selected list index for the head, body, and leg BodyPartFragments
        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, and 2 for the leg fragment
        // Dividing by 12 gives us these integer values because each list of images resources has a size of 12
        int clickedBodyPartNumber = position / 12;

        //store correct list index - always between 0-11
        int clickedListIndex = position - 12*clickedBodyPartNumber;

        //set the currently displayed item for the correct respective body part fragment
        switch (clickedBodyPartNumber) {
            case 0: headIndex = clickedListIndex;
                break;
            case 1: bodyIndex = clickedListIndex;
                break;
            case 2: legIndex = clickedListIndex;
                break;
            default: break;
        }
        //put the info in a Bundle and attach it to an Intent that calls AndroidMakeoverActivity
        Bundle bundle = new Bundle();
        bundle.putInt("headIndex", headIndex);
        bundle.putInt("bodyIndex", bodyIndex);
        bundle.putInt("legIndex", legIndex);

        //attach bundle to intent
        final Intent intent = new Intent(this, AndroidMakeoverActivity.class);
        intent.putExtras(bundle);

        //get ref to NextButton and launch intent when this button is clicked
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

}
