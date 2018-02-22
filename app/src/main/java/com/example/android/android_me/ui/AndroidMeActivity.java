/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            // Create new object of BodyPartFragment for head
            BodyPartFragment headFragment = new BodyPartFragment();

            // Set the image of head
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(1);

            // Use a FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            // Fragment transaction for head
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    //.add(R.id.body_container, bodyFragment)
                    //.add(R.id.leg_container, legFragment)
                    .commit();

            //Create new object of BodyPartFragment for main body
            BodyPartFragment bodyFragment = new BodyPartFragment();

            // Set the image of body
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            bodyFragment.setListIndex(1);

            // Fragment transaction for body
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            //Create new object of BodyPartFragment for Legs
            BodyPartFragment legFragment = new BodyPartFragment();

            // Set the image of legs
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            legFragment.setListIndex(1);

            // Fragment transaction for legs
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();

        }
    }

}
