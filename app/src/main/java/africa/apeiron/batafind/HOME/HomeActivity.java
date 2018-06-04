package africa.apeiron.batafind.HOME;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import africa.apeiron.batafind.R;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    BottomBarTab men,ladies;
    //Toolbar mToolbar;
    String[] titles = {
            "Android",
            "Beta",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Nougat",
            "Oreo",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

      /*  mToolbar = findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }*/

        //Horizontal Scrolling
        HorizontalAdapter firstAdapter = new HorizontalAdapter(titles);
        MultiSnapRecyclerView firstRecyclerView = findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        mRecyclerView = findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        HomeAdapter adapter = new HomeAdapter(this);
        mRecyclerView.setAdapter(adapter);
        HomeClass decoration = new HomeClass(16);
        mRecyclerView.addItemDecoration(decoration);

        BottomBar bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                //messageView.setText(TabMessage.get(tabId, false));
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        men = bottomBar.getTabWithId(R.id.men);
        ladies = bottomBar.getTabWithId(R.id.ladies);
        //ladies.setBadgeCount(5);
        //men.setBadgeCount(2);
    }
}
