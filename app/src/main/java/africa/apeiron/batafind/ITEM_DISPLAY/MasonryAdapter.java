package africa.apeiron.batafind.ITEM_DISPLAY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import africa.apeiron.batafind.ITEM_DETAILS.SelectedItem;
import africa.apeiron.batafind.R;

/**
 * Created by Granson on 25-Mar-18.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private Context context;
    MasonryView holder;
    SharedPreferences Liked_preferences;
    SharedPreferences.Editor editor;

    ArrayList<String> shoe_image = new ArrayList<>();
    ArrayList<String> shoe_name = new ArrayList<>();
    ArrayList<String> shoe_link = new ArrayList<>();
    ArrayList<String> Liked_items = new ArrayList<>();
    String[] shoe_price;

    public MasonryAdapter(Context context, ArrayList<String> image,ArrayList<String> name,ArrayList<String> link,String[] Prices) {
        this.context = context;
        this.shoe_image = image;
        this.shoe_name = name;
        this.shoe_link = link;
        this.shoe_price = Prices;

    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, final int position) {
        this.holder = holder;
        Liked_preferences = context.getSharedPreferences("Bata_Liked_Shoes",
                Context.MODE_PRIVATE);
        editor = Liked_preferences.edit();

        //Get all liked items from preferences
        String all_items = Liked_preferences.getString("Bata_Liked_Shoes",null);
        if(all_items != null){
            Liked_items = new ArrayList<>(Arrays.asList(all_items.split(",")));
        }


        Picasso.get().load(shoe_image.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,shoe_price[position]+ " < > " + shoe_name.get(position),Toast.LENGTH_LONG).show();

                Intent I = new Intent(context, SelectedItem.class);
                I.putExtra("shoe_image",shoe_image.get(position));
                I.putExtra("shoe_name",shoe_name.get(position));
                I.putExtra("shoe_link",shoe_link.get(position));
                I.putExtra("shoe_price",shoe_price[position]);
                context.startActivity(I);
            }
        });

        holder.like_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favourites_toggle(shoe_name.get(position),position);
            }
        });
        color_toggle(shoe_name.get(position));
        holder.textView.setText(shoe_name.get(position));
    }

    @Override
    public int getItemCount() {
        return shoe_name.size();
    }


    public void favourites_toggle(String name,int position){
        if(Liked_items.contains(name)){
            Liked_items.remove(name);
            holder.like_toggle.setImageResource(R.drawable.fave_gray);
            editor.putString("Liked_shoes_list", Liked_items.toString());
            editor.commit();
        }else{
            Liked_items.add(name);
            holder.like_toggle.setImageResource(R.drawable.fave_red);
            Toast.makeText(context,shoe_name.get(position) + " added to favourites",Toast.LENGTH_LONG).show();
            editor.putString("Liked_shoes_list", Liked_items.toString());
            editor.commit();
        }
    }

    public void color_toggle(String name){
        if(Liked_items.isEmpty()){
            //No items to work with
        }else{
            if(Liked_items.contains(name)){
                holder.like_toggle.setImageResource(R.drawable.fave_red);
            }else{
                holder.like_toggle.setImageResource(R.drawable.fave_gray);
            }
        }
    }
    class MasonryView extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView like_toggle;
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            like_toggle = itemView.findViewById(R.id.like_toggle);
            imageView.setClipToOutline(true);
            textView = itemView.findViewById(R.id.img_name);


        }
    }
}
