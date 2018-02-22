package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quinatzin on 2/21/2018.
 */

public class BodyPartFragment extends Fragment {

    public static final String IMAGE_ID_LIST = "image_id";
    public static final String LIST_INDEX = "list_index";

    // Tag for logging of Image id
    private static final String TAG = "BodyPartFragment";

    // List of Images
    private List<Integer> mImageIds;

    // Index of list of image ids;
    private int mListIndex;

    //Empty Constructor
    public BodyPartFragment() {
    }

    /**
     * This inflates fragment layout and sets any image resources
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Load the save state (the list of images and list index) if there is one
        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);


        }
        // Inflate the Android_Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //  Get the reference from the the imageView
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // Set the image to resource that shall be displayed in Android_Me
        if (mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));

            //Setting an on click listener to imageView
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Run through the list from start to finish
                    if (mListIndex < mImageIds.size() - 1) {
                        mListIndex++;
                    } else {
                        // if reach the end of list return to starting point
                        mListIndex = 0;
                    }
                    // Set the image resource to the new list item
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.v(TAG, "Fragment has null list of images of id's");
        }

        // Return the root view
        return rootView;
    }

    /**
     * Used to set imageIds
     *
     * @param imageIds
     */
    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    /**
     * Used to set index of Image
     *
     * @param index
     */
    public void setListIndex(int index) {
        mListIndex = index;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);
    }
}
