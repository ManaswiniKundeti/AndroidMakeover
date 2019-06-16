package com.example.androidmakeoverapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.androidmakeoverapp.R;
import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    //final strings to store state information- used in saveInstanceState
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    //used for logging
    private static final String TAG = BodyPartFragment.class.getSimpleName();
    //var to store list of image resources and index of the image
    // that this fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    //used to instantiate fragment
    public BodyPartFragment() {
    }

    //Inflates the fragment layout file and sets the correct resource for the image to display
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Load save state(if there is one)
        if(savedInstanceState != null){
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //inflate body_part fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        //get ref to imageView in fragment layout
        final ImageView bodyImageView = rootView.findViewById(R.id.body_part_image_view);

        //bodyImageView.setImageResource(ImageAssets.getHeads().get(0));
        //if list of image id's exist, set image resource to the correct item in that list
        if(mImageIds != null){
            //set image resource to list item at stored index
            //if list is not null, then instead of setting the image res to the 1st head image,
            // we'll set it to the image whose image id is in the List<Imageids> at the desired listIndex
            bodyImageView.setImageResource(mImageIds.get(mListIndex));

            //set clicklister on image view
            bodyImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //inc position as long as index <= size of imageIds list
                    if(mListIndex < mImageIds.size()-1){
                        mListIndex++;
                    } else {
                        //if reached end of list, return to beginning index
                        mListIndex = 0;
                    }
                    //set image res to new list Item
                    bodyImageView.setImageResource(mImageIds.get(mListIndex));
                }
            });


        } else {
            //if list of ids's is ull, create log msg
            Log.v(TAG, "This fragment has a null list of image Id's");
        }


        return rootView;
    }

    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    //save current state of fragment on a bundle so that even when the screen is rotated the state of the image is maintained
    @Override
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
