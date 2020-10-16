package com.example.school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        //this is to add tabs and name of the tabs
        //i.e; three tabs which homepage is going to show

        tabLayout.addTab(tabLayout.newTab().setText("School details"));
        tabLayout.addTab(tabLayout.newTab().setText("Facilities"));
        tabLayout.addTab(tabLayout.newTab().setText("Owner details"));

        //adding the tabs to the adapter so that the fragments can show all the tabs

        final DetailsAdapter adapter = new DetailsAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    // initiated menu in our HomePage Activity so that some menu options can be created for the app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // will show an alert for log out when clicked from the menu
        if(id == R.id.action_logout){
            alertForLogOut();
        }
        //if Fill Details option is selected, user will be sent to the Details Activity
        if(id == R.id.action_details){
            startActivity(new Intent(HomePage.this,DetailsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //creating a dialog alert , when the user want to log out from the app
    private void alertForLogOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this); //Builder for building the alert dialoge for the log out button
        //message is set using this code which is to be appeared on the alert
        //positive button saying Yes will make user log out from the app safely
        // negative button saying No will simply close the dialog
        builder.setMessage("Are you sure to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        startActivity(new Intent(HomePage.this,MainActivity.class));
                    }
                })
                .setNegativeButton("No",null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}