package africa.apeiron.batafind.GROUPED_CATEGORY;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import africa.apeiron.batafind.R;

public class GroupedCategoryAction extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String category;
    Intent X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grouped_category);

        X = getIntent();
        category = X.getStringExtra("Name");

        mRecyclerView = findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        GroupedCategoryAdapter adapter = new GroupedCategoryAdapter(this,category);
        mRecyclerView.setAdapter(adapter);
        GCSpacesItemDecoration decoration = new GCSpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

    }

}
