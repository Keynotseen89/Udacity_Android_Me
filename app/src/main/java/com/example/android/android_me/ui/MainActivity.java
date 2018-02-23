package com.example.android.android_me.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;


/**
 * Created by Quinatzin Sintora on 2/21/2018.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    // Variables to store values for the list index
    private int headIndex, bodyIndex, legIndex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;
            // Getting rid of next button
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            Button nextBtn = (Button) findViewById(R.id.next_btn);
            nextBtn.setVisibility(View.GONE);

            if (savedInstanceState == null) {
                FragmentManager fm = getSupportFragmentManager();
                //Used for head fragment
                BodyPartFragment headFrag = new BodyPartFragment();
                headFrag.setImageIds(AndroidImageAssets.getHeads());
                fm.beginTransaction()
                        .add(R.id.head_container, headFrag)
                        .commit();

                // Used for body fragment
                BodyPartFragment bodyFrag = new BodyPartFragment();
                bodyFrag.setImageIds(AndroidImageAssets.getBodies());
                fm.beginTransaction()
                        .add(R.id.body_container, bodyFrag)
                        .commit();

                // Used for leg fragment
                BodyPartFragment legFrag = new BodyPartFragment();
                legFrag.setImageIds(AndroidImageAssets.getLegs());
                fm.beginTransaction()
                        .add(R.id.leg_container, legFrag)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    public void onImageSelected(int post) {
        Toast.makeText(this, " Position clicked = " + post, Toast.LENGTH_SHORT).show();

        // Obtain the correct index for each body part
        int bodyPartNum = post / 12;
        int listIndex = post - 12 * bodyPartNum;

        if (mTwoPane) {
            // Handle twoPane case
            BodyPartFragment newFrag = new BodyPartFragment();

            // Set the current display item for current body part fragment
            switch (bodyPartNum) {
                case 0:
                    newFrag.setImageIds(AndroidImageAssets.getHeads());
                    newFrag.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFrag)
                            .commit();
                    break;
                case 1:
                    newFrag.setImageIds(AndroidImageAssets.getBodies());
                    newFrag.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFrag)
                            .commit();
                    break;
                case 2:
                    newFrag.setImageIds(AndroidImageAssets.getLegs());
                    newFrag.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFrag)
                            .commit();
                    break;
                default:
                    break;
            }

        } else {
            // Switch statement to set current item of body part
            switch (bodyPartNum) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 3:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
            // Used to attach to an intent to launch AndroidMeActivity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            // Attach it to a new intent the launch new activiy
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            // The next launches a new AndroidMeActivity
            Button nextButton = (Button) findViewById(R.id.next_btn);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });

        }
    }
}
