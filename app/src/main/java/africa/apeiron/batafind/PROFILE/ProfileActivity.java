package africa.apeiron.batafind.PROFILE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY.GCSpacesItemDecoration;
import africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY.GroupedCategoryAdapter;
import africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY.Profile_Decorator;
import africa.apeiron.batafind.R;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        LayoutInflater inflater = getLayoutInflater();
        getWindow().addContentView(inflater.inflate(R.layout.floating_profile, null),
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        mRecyclerView = findViewById(R.id.masonry_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        GroupedCategoryAdapter adapter = new GroupedCategoryAdapter(this,"Men");
        mRecyclerView.setAdapter(adapter);
        Profile_Decorator decoration = new Profile_Decorator(30);
        mRecyclerView.addItemDecoration(decoration);
    }
}
