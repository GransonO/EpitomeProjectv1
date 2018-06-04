package africa.apeiron.batafind.GROUPED_CATEGORY;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import africa.apeiron.batafind.ITEM_DISPLAY.DisplayProducts;
import africa.apeiron.batafind.R;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Granson on 25-Mar-18.
 */
public class GroupedCategoryAdapter extends RecyclerView.Adapter<GroupedCategoryAdapter.MasonryView> {

    private Context context;
    String url;
    SweetAlertDialog pDialog;

    ArrayList<String> shoe_image = new ArrayList<>();
    ArrayList<String> shoe_name = new ArrayList<>();
    ArrayList<String> shoe_link = new ArrayList<>();
    String[] result;
    ArrayList<String> shoe_prices = new ArrayList<>();

    int[] imgList;
    String[] linksList;
    String[] nameList;

//All kids
    int[] kids_img = {R.drawable.school,R.drawable.playing_kids,R.drawable.boys,
            R.drawable.girls,R.drawable.infants, R.drawable.safari,R.drawable.rainbow,
            R.drawable.gal_vans,R.drawable.boy_vans,R.drawable.girls_dress,
            R.drawable.dress_boys
    };

    String[] kids_list = {"School","All kids","Boys Style","Girls Style",
            "Infants","Safari Kids","Shine","Girl Casual","Boys casuals","Girls dress","Boys Dress"
    };

    String[] kids_links = {"http://batakenya.com/batakenya/c-5433_cl-70/school.html",
            "http://batakenya.com/batakenya/c-5172_cl-70/kids.html",
            "http://batakenya.com/batakenya/c-5236_cl-70/kids/boys/casual.html",
            "http://batakenya.com/batakenya/c-5246_cl-70/kids/girls/sandal.html",
            "http://batakenya.com/batakenya/c-5233_cl-70/kids/infants/sandals.html",
            "http://batakenya.com/batakenya/c-5431_cl-70/safari.html",
            "http://batakenya.com/batakenya/c-5237_cl-70/kids/boys/sports.html",
            "http://batakenya.com/batakenya/c-5242_cl-70/kids/girls/casual.html",
            "http://batakenya.com/batakenya/c-5234_cl-70/kids/infants/casual.html",
            "http://batakenya.com/batakenya/c-5241_cl-70/kids/girls/dress.html",
            "http://batakenya.com/batakenya/c-5238_cl-70/kids/boys/canvas.html"
    };

//All ladies
    int[] ladies_img = {R.drawable.lady,R.drawable.bata_office_lady,R.drawable.ladies_opens,
            R.drawable.her_style,R.drawable.lady_north,R.drawable.marie_claire,R.drawable.tommi_ladies,R.drawable.balerina,
            R.drawable.patapata,R.drawable.sports
    };

    String[] ladies_list = {"All Ladies","Lady Official","Open Shoes","Bata Collection","North Stars",
            "Marie Claire","Tommy Ladies","Ballerina","Patapata","Sports"
    };

    String[] ladies_links = {"http://batakenya.com/batakenya/c-5170_cl-70/ladies.html",
            "http://batakenya.com/batakenya/c-5176_cl-70/ladies/dress.html","http://batakenya.com/batakenya/c-5178_cl-70/ladies/sandals.html",
            "http://batakenya.com/batakenya/c-5177_cl-70/ladies/casual.html","http://batakenya.com/batakenya/br-75_cl-70_c-5170/northstar/ladies.html","http://batakenya.com/batakenya/br-70_cl-70_c-5170/marie-claire/ladies.html",
            "http://batakenya.com/batakenya/c-5177_cl-70/ladies/casual.html","http://batakenya.com/batakenya/br-71_cl-70_c-5170/bata/ladies.html","http://batakenya.com/batakenya/c-5434_cl-70/patapata.html","http://batakenya.com/batakenya/c-5435_cl-70/sports.html"
    };

//All men
    int[] men_img = { R.drawable.for_men,R.drawable.safari,R.drawable.leather_shoes,
            R.drawable.bata_casual,R.drawable.beach,R.drawable.weinbrenner,R.drawable.tommy_men,
            R.drawable.bata_office_man,R.drawable.north_men,R.drawable.patapata,R.drawable.sports
    };

    String[] men_lists = {"All Men", "Safari","Official","Casual","Sandals",
            "Weinbrenner","Tommy Men","Bata collection","North Star",
            "Patapata","Sports"
    };

    String[] men_links = {"http://batakenya.com/batakenya/c-5171_cl-70/men.html",
            "http://batakenya.com/batakenya/c-5431_cl-70/safari.html","http://batakenya.com/batakenya/c-5182_cl-70/men/dress.html",
            "http://batakenya.com/batakenya/c-5183_cl-70/men/casual.html","http://batakenya.com/batakenya/c-5184_cl-70/men/sandals.html","http://batakenya.com/batakenya/br-77_cl-70_c-5171/weinbreinner/men.html",
            "http://batakenya.com/batakenya/br-90_cl-70_c-5171/tomy-takkies/men.html","http://batakenya.com/batakenya/br-71_cl-70_c-5171/bata/men.html","http://batakenya.com/batakenya/br-75_cl-70_c-5171/northstar/men.html",
            "http://batakenya.com/batakenya/c-5434_cl-70/patapata.html","http://batakenya.com/batakenya/c-5435_cl-70/sports.html"
    };



    public GroupedCategoryAdapter(Context context, String category) {

        this.context = context;

        switch (category){
            case "Kids":

                imgList = kids_img;
                nameList = kids_list;
                linksList = kids_links;
                break;

            case "Men":

                imgList = men_img;
                nameList = men_lists;
                linksList = men_links;
                break;

            case "Ladies":

                imgList = ladies_img;
                nameList = ladies_list;
                linksList = ladies_links;
                break;
        }

    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, final int position) {

        Picasso.get().load(imgList[position]).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoe_prices.clear();
                //Toast.makeText(context,nameList[position],Toast.LENGTH_LONG).show();
                url = linksList[position];
                new Description().execute();
            }
        });
        holder.textView.setText(nameList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }


    class MasonryView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            imageView.setClipToOutline(true);
            textView = itemView.findViewById(R.id.img_name);
        }
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#ff0000"));
            pDialog.setTitleText("Fetching...");
            pDialog.setContentText("Preparing heaven for you :}");
            pDialog.setCancelable(false);
            pDialog.show();

            shoe_image.clear();
            shoe_name.clear();
            shoe_link.clear();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();

                Elements images = document.getElementsByClass("product-image");
                Elements price = document.getElementsByClass("changeCurr");
                result = ((price.html().replace("[","")).replace("]","")).split("\n");

                //shoe_prices = new ArrayList<String>(result);

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
            pDialog.dismissWithAnimation();

            //Toast.makeText(context,result[0],Toast.LENGTH_LONG).show();

            Intent X = new Intent(context,DisplayProducts.class);
            X.putStringArrayListExtra("shoe_image",shoe_image);
            X.putStringArrayListExtra("shoe_name",shoe_name);
            X.putStringArrayListExtra("shoe_link",shoe_link);
            X.putExtra("shoe_prices",result);
            context.startActivity(X);

        }
    }
}
