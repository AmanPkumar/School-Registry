package com.example.school;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.school.fragments.FacilitiesFragment;
import com.example.school.fragments.OwnerFragment;
import com.example.school.fragments.SchoolFragment;

public class DetailsAdapter extends FragmentPagerAdapter {
    Context context;
    int numberOfTabs;

    // context, fragments and the number of tabs are used as arguments for the constructor
    //Details adapter will give the final fragments view to our app
    public DetailsAdapter(Context context,FragmentManager fm,int tabs){
        super(fm);
        this.context = context;
        this.numberOfTabs = tabs;
    }

    // showing the tabs to the screen and the position is set here
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                SchoolFragment schoolFragment = new SchoolFragment();
                return schoolFragment;
            case 1:
                FacilitiesFragment facilitiesFragment = new FacilitiesFragment();
                return facilitiesFragment;
            case 2:
                OwnerFragment ownerFragment = new OwnerFragment();
                return ownerFragment;
            default:
                return null;
        }
    }
    //showing required tabs i.e; numberOfTabs
    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
