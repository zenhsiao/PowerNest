package com.codepath.powernest;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.powernest.fragment.FilterDialogFragment;
import com.codepath.powernest.fragment.StarClubFragment;


public class HomeEventActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ViewPager vpPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_event);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);


        setupDrawerContent(nvDrawer);

        drawerLayout.addDrawerListener(drawerToggle);


        getSupportActionBar().setTitle("Power Lobby");
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setAdapter(new EventsPageAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setViewPager(vpPager);





    }

    public class EventsPageAdapter extends FragmentPagerAdapter {
        private String tabTitle[] =  new String[]{"Your Group","All Group"};

        public EventsPageAdapter (FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return tabTitle.length;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                StarClubFragment strFragment = new StarClubFragment ();
                return strFragment;
            } else {
                StarClubFragment strFragment = new StarClubFragment ();
                return strFragment;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitle[position];
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_lobby:
                fragmentClass = HomeEventActivity.class;
                break;
            case R.id.nav_calendar:
                fragmentClass = HomeEventActivity.class;
                break;
            case R.id.nav_group:
                fragmentClass = HomeEventActivity.class;
                break;
            case R.id.nav_rush:
                fragmentClass = HomeEventActivity.class;
                break;
            case R.id.nav_setting:
                fragmentClass = HomeEventActivity.class;
                break;
            case R.id.nav_notification:
                fragmentClass = HomeEventActivity.class;
                break;

            default:
                fragmentClass = HomeEventActivity.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.other, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        switch (item.getItemId()) {
            case R.id.nav_calendar:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    public void searchOnMap(MenuItem v){
        Intent i = new Intent(this,SearchOnMapActivity.class);
        startActivity(i);

    }

    public void filter(MenuItem v){
        Intent i = new Intent(this,SearchOnMapActivity.class);
        startActivity(i);

    }

    public void showFilterDialog(){
        FragmentManager fm = getSupportFragmentManager();
        FilterDialogFragment fragment = FilterDialogFragment.newInstance("Some Title");
        fragment.show(fm,"fragment_edit_name");

    }

    public boolean showDialog(){
        return true;
    }



}
