package com.example.mad_1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mad_1.ui.main.SectionsPagerAdapter;
import com.example.mad_1.databinding.ActivityGetRestBinding;

public class GetRestActivity extends AppCompatActivity {

    private ActivityGetRestBinding binding;

    TabLayout tabs;
    ViewPager viewPager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGetRestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.myDrawerLayout;
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = binding.navMenu;

        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent;

            switch (item.getItemId()) {
                case R.id.lab2:
                    intent = new Intent(this, ThreadedActivityMain.class);
                    startActivity(intent);
                    break;

                case R.id.lab4:
                    intent = new Intent(this, RegistrationActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab5:
                    intent = new Intent(this, StudentMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab6:
                    intent = new Intent(this, NavigationMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab7:
                    intent = new Intent(this, SecondActivityCam.class);
                    startActivity(intent);
                    break;

                case R.id.lab8:
                    intent = new Intent(this, GetRestActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab9:
                    intent = new Intent(this, AttendanceMainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.lab91:
                    intent = new Intent(this, SearchStudentActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        });

        // set and define tabs
        tabs = binding.tabs;
        tabs.addTab(tabs.newTab().setText("Get Joke"));
        tabs.addTab(tabs.newTab().setText("Get University"));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), tabs.getTabCount());
        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}