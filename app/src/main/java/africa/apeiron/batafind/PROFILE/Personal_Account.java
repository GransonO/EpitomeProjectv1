package africa.apeiron.batafind.PROFILE;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import africa.apeiron.batafind.FRAGMENTS.FragmentHome;
import africa.apeiron.batafind.FRAGMENTS.FragmentKids;
import africa.apeiron.batafind.FRAGMENTS.FragmentLadies;
import africa.apeiron.batafind.FRAGMENTS.FragmentMen;
import africa.apeiron.batafind.R;


public class Personal_Account extends AppCompatActivity {

    ViewPager viewPager;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        final TextView toolbar_title = toolbar.findViewById(R.id.toolbar_title);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("");
                    toolbar_title.setText("Granson Oyombe");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    toolbar_title.setText("");
                    isShow = false;
                }
            }
        });

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Likes", FragmentHome.class)
                .add("Collection", FragmentMen.class)
                .add("Following", FragmentLadies.class)
                .add("Followers", FragmentKids.class)
                .create());

        viewPager = findViewById(R.id.viewpager);

        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }

}
