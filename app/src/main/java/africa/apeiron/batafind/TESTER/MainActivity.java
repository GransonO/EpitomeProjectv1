package africa.apeiron.batafind.TESTER;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import africa.apeiron.batafind.FRAGMENTS.FragmentHome;
import africa.apeiron.batafind.FRAGMENTS.FragmentKids;
import africa.apeiron.batafind.FRAGMENTS.FragmentLadies;
import africa.apeiron.batafind.FRAGMENTS.FragmentMen;
import africa.apeiron.batafind.HOME.HomeActivity;
import africa.apeiron.batafind.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Context context;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

       FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Men", FragmentHome.class)
                .add("Ladies", FragmentMen.class)
                .add("Kids", FragmentLadies.class)
                .create());

        viewPager = findViewById(R.id.viewpager);

        //FragmentManager fragmentManager = getSupportFragmentManager();
        //viewPager.setAdapter(new MyAdapter(fragmentManager));
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

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
