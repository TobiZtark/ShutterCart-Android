package com.app.shuttercart.activity;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.shuttercart.R;
import com.app.shuttercart.fragment.HomeFragment;
import com.app.shuttercart.fragment.NavigationFragment;
import com.app.shuttercart.fragment.ProfileFragment;
import com.app.shuttercart.fragment.RebateProductFragment;
import com.app.shuttercart.fragment.ReceiptFragment;
import com.app.shuttercart.fragment.SettingsFragment;
import com.app.shuttercart.fragment.SurveyFragment;
import com.app.shuttercart.fragment.VideosFragment;
import com.app.shuttercart.fragment.WalletFragment;
import com.app.shuttercart.util.Const;

public class MainActivity extends AppCompatActivity implements NavigationFragment.NavigationMenuListener{

    private NavigationFragment navigationFragment;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Handler handler;

    private static String CURRENT_TAG = Const.TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        setUpDrawer(drawerLayout, toolbar);

        loadFragment(Const.TAG_HOME, null);
    }

    private void loadFragment(final String action, final Bundle bundle){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragment(action, bundle);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content_container, fragment, CURRENT_TAG);
                fragmentTransaction.addToBackStack(CURRENT_TAG);
                fragmentTransaction.commit();
                setTitle(action);
            }
        };
        handler.post(runnable);
    }

    private void setTitle(String action){
        if (action.equals(Const.TAG_HOME)){
            toolbar.setTitle("ShutterCart");
            toolbar.setBackgroundResource(R.color.colorAccent);
        } else {
            toolbar.setTitle(action);
            toolbar.setBackgroundResource(R.color.colorPrimaryDark);
        }
    }

    private Fragment getFragment(String action, Bundle bundle){
        switch (action){
            case Const.TAG_HOME:
                HomeFragment homeFragment = new HomeFragment();
                CURRENT_TAG =  Const.TAG_HOME;
                return homeFragment;
            case Const.TAG_REBATE_PRODUCTS:
                RebateProductFragment rebateProductFragment = new RebateProductFragment();
                CURRENT_TAG =  Const.TAG_REBATE_PRODUCTS;
                return rebateProductFragment;
            case Const.TAG_SURVEY:
                SurveyFragment surveyFragment = new SurveyFragment();
                CURRENT_TAG = Const.TAG_SURVEY;
                return surveyFragment;
            case Const.TAG_VIDEOS:
                VideosFragment videosFragment = new VideosFragment();
                CURRENT_TAG = Const.TAG_VIDEOS;
                return videosFragment;
            case Const.TAG_RECEIPTS:
                ReceiptFragment receiptFragment = new ReceiptFragment();
                CURRENT_TAG = Const.TAG_RECEIPTS;
                return receiptFragment;
            case Const.TAG_WALLET:
                WalletFragment walletFragment = new WalletFragment();
                CURRENT_TAG = Const.TAG_WALLET;
                return walletFragment;
            case Const.TAG_PROFILE:
                ProfileFragment profileFragment = new ProfileFragment();
                CURRENT_TAG = Const.TAG_PROFILE;
                return profileFragment;
            case Const.TAG_SETTINGS:
                SettingsFragment settingsFragment = new SettingsFragment();
                CURRENT_TAG = Const.TAG_SETTINGS;
                return settingsFragment;
            default:
                toolbar.setTitle("ShutterCart");
                return new HomeFragment();
        }
    }

    public void setUpDrawer(final DrawerLayout drawerLayout, Toolbar toolbar){
        this.drawerLayout = drawerLayout;
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        drawerLayout.addDrawerListener(this.drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    @Override
    public void onMenuSelected(String menuItem) {
        loadFragment(menuItem, null);
        drawerLayout.closeDrawers();
    }
}
