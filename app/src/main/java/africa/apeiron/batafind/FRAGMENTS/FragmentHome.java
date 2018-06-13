package africa.apeiron.batafind.FRAGMENTS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import africa.apeiron.batafind.HOME.HomeAdapter;
import africa.apeiron.batafind.HOME.HomeClass;
import africa.apeiron.batafind.HOME.HorizontalAdapter;
import africa.apeiron.batafind.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentHome extends Fragment {

    private Context context;
    SweetAlertDialog newDialog,pop_Dialog;

    ArrayList<String> new_shoe_image = new ArrayList<>(),shoe_image = new ArrayList<>();
    ArrayList<String> new_shoe_name = new ArrayList<>(),shoe_name = new ArrayList<>();
    ArrayList<String> new_shoe_link = new ArrayList<>(),shoe_link = new ArrayList<>();
    String[] new_result,result;

    MultiSnapRecyclerView firstRecyclerView;

    RecyclerView mRecyclerView;

    public FragmentHome() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = container.getContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firstRecyclerView = view.findViewById(R.id.first_recycler_view);
        mRecyclerView = view.findViewById(R.id.home_grid);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        if(savedInstanceState == null){

            //populate new arrivals
            new NewArrivalsDescription().execute();

            //Populate popular
            new PopularDescription().execute();

        }else{
            savedDataState(savedInstanceState);
        }

        return view;

    }

    public void savedDataState(Bundle savedInstanceState){

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
            newDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            newDialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
            newDialog.setTitleText("Fetching...");
            newDialog.setContentText("New arrivals.");
            newDialog.setCancelable(false);
            newDialog.show();

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
            newDialog.dismissWithAnimation();

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
            pop_Dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pop_Dialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
            pop_Dialog.setTitleText("Sorting...");
            pop_Dialog.setContentText("Popular products");
            pop_Dialog.setCancelable(false);
            pop_Dialog.show();

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
            pop_Dialog.dismissWithAnimation();

            //Staggered grid
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