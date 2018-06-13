package africa.apeiron.batafind.FRAGMENTS;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY.GCSpacesItemDecoration;
import africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY.GroupedCategoryAdapter;
import africa.apeiron.batafind.R;

public class FragmentKids extends Fragment {

    RecyclerView mRecyclerView;

    public FragmentKids() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentlayout, container, false);

        mRecyclerView = view.findViewById(R.id.masonry_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        GroupedCategoryAdapter adapter = new GroupedCategoryAdapter(getActivity(),"Kids");
        mRecyclerView.setAdapter(adapter);
        GCSpacesItemDecoration decoration = new GCSpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        return view;}
}