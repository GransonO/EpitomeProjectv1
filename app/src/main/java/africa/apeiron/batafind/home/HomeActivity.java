package africa.apeiron.batafind.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import africa.apeiron.batafind.R;
import africa.apeiron.batafind.fragments.FragmentHome;
import africa.apeiron.batafind.fragments.FragmentKids;
import africa.apeiron.batafind.fragments.FragmentLadies;
import africa.apeiron.batafind.fragments.FragmentMen;
import africa.apeiron.batafind.profile.Personal_Account;
import africa.apeiron.batafind.profile.ProfileActivity;

public class HomeActivity extends AppCompatActivity {

    BottomBar bottomBar;
    ViewPager viewPager;
    Context context;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        context = this;
        //calls the pager into the activity
        viewPager = findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){

                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.men:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.ladies:

                        viewPager.setCurrentItem(2);
                        break;

                    case R.id.kids:
                        viewPager.setCurrentItem(3);
                        break;

//                    case R.id.profile:
//                        Intent I = new Intent(HomeActivity.this, Personal_Account.class);
//                        startActivity(I);
//                        break;
//                        This should be added to to the profile activity

                    case R.id.profile:
                        Intent x = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(x);
                        break;


                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if( tabId == R.id.profile){
                    Intent I = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(I);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //ladies.setBadgeCount(5);
        //men.setBadgeCount(2);


    }


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        //returns the position of fragment in view
        public Fragment getItem(int position) {

            if (position == 0) {
                fragment = new FragmentHome();
            }
            if (position == 1) {
                fragment = new FragmentMen();
            }
            if (position == 2) {
                fragment = new FragmentLadies();
            }
            if (position == 3) {
                fragment = new FragmentKids();
            }

            return fragment;
        }

        @Override
        //gets total count of all fragments available
        public int getCount() {
            return 4;
        }


    }
}
