package com.example.androidmakeoverapp.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.androidmakeoverapp.R;
import com.example.androidmakeoverapp.data.ImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
    //var to store values for list index of the selected images
    //default value index=0
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Determine if you're creating a two-pane or single-pane display
        if(findViewById(R.id.android_makeover_linear_layout) != null) {
            //linear layout exists only in 2 pane case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
            Button nextButton = findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageIds(ImageAssets.getHeads());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_fragment_container, headFragment)
                        .commit();

                // New body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageIds(ImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_fragment_container, bodyFragment)
                        .commit();

                // New leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageIds(ImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_fragment_container, legFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }
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

        //handle image selected for 2 pane case
        if(mTwoPane) {
            //handle 2 pane case

            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (clickedBodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setmImageIds(ImageAssets.getHeads());
                    newFragment.setmListIndex(clickedListIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_fragment_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageIds(ImageAssets.getBodies());
                    newFragment.setmListIndex(clickedListIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_fragment_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageIds(ImageAssets.getLegs());
                    newFragment.setmListIndex(clickedListIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_fragment_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
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

}
