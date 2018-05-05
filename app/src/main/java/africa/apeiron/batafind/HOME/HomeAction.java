package africa.apeiron.batafind.HOME;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import africa.apeiron.batafind.R;

public class HomeAction extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String category;
    Intent X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        X = getIntent();
        category = X.getStringExtra("Name");

        mRecyclerView = findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        HomeAdapter adapter = new HomeAdapter(this,category);
        mRecyclerView.setAdapter(adapter);
        HomeSpacesItemDecoration decoration = new HomeSpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

    }

}
