package africa.apeiron.batafind.CATEGORIES;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import africa.apeiron.batafind.R;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        mToolbar = findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        mRecyclerView = findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        CategoryAdapter adapter = new CategoryAdapter(this);
        mRecyclerView.setAdapter(adapter);
        Category_class decoration = new Category_class(16);
        mRecyclerView.addItemDecoration(decoration);
    }
}
