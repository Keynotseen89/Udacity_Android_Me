package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Quinatzin on 2/21/2018.
 */

public class MasterListFragment extends Fragment {

    public MasterListFragment() {
    }

    // Creating a variable Image Clicked
    OnImageClickListener mCallback;

    // Creating an interface
    public interface OnImageClickListener {
        void onImageSelected(int post);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // obtain rootView by id from xml layout
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // obtain the GridView location from its id
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        // This creates an Adapter from MasterListAdapter and obtains all the image content
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Sets the gridView to the adapter which should display all the images
        gridView.setAdapter(mAdapter);

        // Set gridView to on Image clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageSelected(position);
            }
        });

        // Returns the View
        return rootView;

    }

}
