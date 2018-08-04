package africa.apeiron.batafind.HOME;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import africa.apeiron.batafind.FRAGMENTS.FragmentHome;
import africa.apeiron.batafind.FRAGMENTS.FragmentKids;
import africa.apeiron.batafind.FRAGMENTS.FragmentLadies;
import africa.apeiron.batafind.FRAGMENTS.FragmentMen;
import africa.apeiron.batafind.ProfileActivity;
import africa.apeiron.batafind.R;
import africa.apeiron.batafind.REGISTRATION.SignUp;

public class HomeActivity extends AppCompatActivity {

    BottomBarTab men,ladies,kids,home;
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
                //messageView.setText(TabMessage.get(tabId, false));
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
                    case R.id.profile:

                        Intent I = new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(I);
                        break;


                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                //Toast.makeText(getApplicationContext(), get(tabId, true), Toast.LENGTH_LONG).show();
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
