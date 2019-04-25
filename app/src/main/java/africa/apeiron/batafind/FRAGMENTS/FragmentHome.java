package africa.apeiron.batafind.FRAGMENTS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.novoda.merlin.MerlinsBeard;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import africa.apeiron.batafind.HOME.HomeActivity;
import africa.apeiron.batafind.HOME.HomeAdapter;
import africa.apeiron.batafind.HOME.HomeClass;
import africa.apeiron.batafind.HOME.HorizontalAdapter;
import africa.apeiron.batafind.R;
import africa.apeiron.batafind.REGISTRATION.SignUp;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentHome extends Fragment {

    private Context context;
    //SweetAlertDialog newDialog,pop_Dialog;

    ArrayList<String> new_shoe_image = new ArrayList<>(),shoe_image = new ArrayList<>();
    ArrayList<String> new_shoe_name = new ArrayList<>(),shoe_name = new ArrayList<>();
    ArrayList<String> new_shoe_link = new ArrayList<>(),shoe_link = new ArrayList<>();
    String[] new_result,result;

    MultiSnapRecyclerView firstRecyclerView;
    RecyclerView mRecyclerView;

    TextView new_arrivals,popular;
    ImageView new_img,pd;

    ShimmerRecyclerView shimmerRecycler;
    MerlinsBeard merlinsBeard;
    Fragment fragment = null;

    public FragmentHome() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = container.getContext();
        merlinsBeard = MerlinsBeard.from(context);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView toolbar_title = view.findViewById(R.id.toolbar_title);

        final CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = view.findViewById(R.id.app_bar);
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
                    toolbar_title.setText("");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    toolbar_title.setText("Popular Items");
                    isShow = false;
                }
            }
        });

        new_arrivals = view.findViewById(R.id.new_arrivals);
        popular = view.findViewById(R.id.popular);

        pd = view.findViewById(R.id.pd);
        new_img = view.findViewById(R.id.new_img);

        new_arrivals.setVisibility(View.GONE);
        popular.setVisibility(View.GONE);

        new_img.setVisibility(View.GONE);
        pd.setVisibility(View.GONE);

        firstRecyclerView = view.findViewById(R.id.first_recycler_view);

        shimmerRecycler = view.findViewById(R.id.shimmer_grid);
        shimmerRecycler.showShimmerAdapter();

        mRecyclerView = view.findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        if (merlinsBeard.isConnected()) {
            if(savedInstanceState == null){

                //populate new arrivals
                new NewArrivalsDescription().execute();

                //Populate popular
                new PopularDescription().execute();

            }else{
                savedDataState(savedInstanceState);
            }

        } else {
            StyleableToast.makeText(context, "Please check your internet connection!", Toast.LENGTH_LONG, R.style.No_Network).show();
        }

        return view;

    }

    public void savedDataState(Bundle savedInstanceState){


        shimmerRecycler.hideShimmerAdapter();

        new_img.setVisibility(View.VISIBLE);
        pd.setVisibility(View.VISIBLE);

        new_arrivals.setVisibility(View.VISIBLE);
        popular.setVisibility(View.VISIBLE);

        new_shoe_image = savedInstanceState.getStringArrayList("new_shoe_image");
        new_shoe_name = savedInstanceState.getStringArrayList("new_shoe_name");
        new_shoe_link = savedInstanceState.getStringArrayList("new_shoe_link");
        new_result = savedInstanceState.getStringArray("new_result");

        shoe_image = savedInstanceState.getStringArrayList("shoe_image");
        shoe_name = savedInstanceState.getStringArrayList("shoe_name");
        shoe_link = savedInstanceState.getStringArrayList("shoe_link");
        result = savedInstanceState.getStringArray("result");

        //Horizontal Scrolling
        HorizontalAdapter firstAdapter = new HorizontalAdapter(context,new_shoe_name,new_shoe_image,new_shoe_link,new_result);
        LinearLayoutManager firstManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        //Staggered grid
        HomeAdapter adapter = new HomeAdapter(getActivity(),shoe_name,shoe_image,shoe_link,result);
        mRecyclerView.setAdapter(adapter);

        HomeClass decoration = new HomeClass(16);
        mRecyclerView.addItemDecoration(decoration);
    }

    private class NewArrivalsDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // newDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
           // newDialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
           // newDialog.setTitleText("Fetching...");
           // newDialog.setContentText("New arrivals.");
           // newDialog.setCancelable(false);
           // newDialog.show();

            new_shoe_image.clear();
            new_shoe_name.clear();
            new_shoe_link.clear();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Get url to new arrivals
                Document document = Jsoup.connect("http://batakenya.com/batakenya/c-5433_cl-70/school.html").get();

                Elements images = document.getElementsByClass("product-image");
                Elements price = document.getElementsByClass("changeCurr");
                new_result = ((price.html().replace("[","")).replace("]","")).split("\n");

                for (Element image : images) {
                    Elements values = image.select("img");
                    Elements link_source = image.select("a");
                    new_shoe_image.add(values.attr("data-original"));
                    new_shoe_name.add(values.attr("title"));
                    new_shoe_link.add(link_source.attr("href"));

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //newDialog.dismissWithAnimation();

            //Horizontal Scrolling
            HorizontalAdapter firstAdapter = new HorizontalAdapter(context,new_shoe_name,new_shoe_image,new_shoe_link,new_result);
            LinearLayoutManager firstManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            firstRecyclerView.setLayoutManager(firstManager);
            firstRecyclerView.setAdapter(firstAdapter);
        }
    }

    private class PopularDescription extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //pop_Dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            //pop_Dialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
            //pop_Dialog.setTitleText("Sorting...");
            //pop_Dialog.setContentText("Popular products");
            //pop_Dialog.setCancelable(false);
            //pop_Dialog.show();

            shoe_image.clear();
            shoe_name.clear();
            shoe_link.clear();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //Get url to new arrivals
                Document document = Jsoup.connect("http://batakenya.com/batakenya/c-5431_cl-70/safari.html").get();

                Elements images = document.getElementsByClass("product-image");
                Elements price = document.getElementsByClass("changeCurr");
                result = ((price.html().replace("[","")).replace("]","")).split("\n");

                for (Element image : images) {
                    Elements values = image.select("img");
                    Elements link_source = image.select("a");
                    shoe_image.add(values.attr("data-original"));
                    shoe_name.add(values.attr("title"));
                    shoe_link.add(link_source.attr("href"));

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //pop_Dialog.dismissWithAnimation();
            //Staggered grid
            shimmerRecycler.hideShimmerAdapter();

            new_img.setVisibility(View.VISIBLE);
            pd.setVisibility(View.VISIBLE);

            new_arrivals.setVisibility(View.VISIBLE);
            popular.setVisibility(View.VISIBLE);

            HomeAdapter adapter = new HomeAdapter(getActivity(),shoe_name,shoe_image,shoe_link,result);
            mRecyclerView.setAdapter(adapter);

            HomeClass decoration = new HomeClass(16);
            mRecyclerView.addItemDecoration(decoration);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("new_shoe_image",new_shoe_image);
        outState.putStringArrayList("new_shoe_name",new_shoe_name);
        outState.putStringArrayList("new_shoe_link",new_shoe_link);
        outState.putStringArray("new_result",new_result);

        outState.putStringArrayList("shoe_image",shoe_image);
        outState.putStringArrayList("shoe_name",shoe_name);
        outState.putStringArrayList("shoe_link",shoe_link);
        outState.putStringArray("result",result);

    }


}