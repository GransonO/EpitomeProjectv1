package africa.apeiron.batafind.item_display;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import africa.apeiron.batafind.R;

public class DisplayProducts extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<String> shoe_image = new ArrayList<>();
    ArrayList<String> shoe_name = new ArrayList<>();
    ArrayList<String> shoe_link = new ArrayList<>();
    String[] price_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_display);

        Intent x = getIntent();
        shoe_image = x.getStringArrayListExtra("shoe_image");
        shoe_name = x.getStringArrayListExtra("shoe_name");
        shoe_link = x.getStringArrayListExtra("shoe_link");
        price_list = x.getStringArrayExtra("shoe_prices");


        mRecyclerView = findViewById(R.id.masonry_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        MasonryAdapter adapter = new MasonryAdapter(this,shoe_image,shoe_name,shoe_link,price_list);
        mRecyclerView.setAdapter(adapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

    }
}
