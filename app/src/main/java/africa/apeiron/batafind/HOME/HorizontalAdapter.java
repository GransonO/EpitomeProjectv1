package africa.apeiron.batafind.HOME;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import africa.apeiron.batafind.R;


/**
 * Created by takusemba on 2017/08/03 Edited by Granson 2018/6/12.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    ArrayList<String> shoe_image;
    ArrayList<String> shoe_name;
    ArrayList<String> shoe_link;
    String[] shoe_price;
    Context context;


    public HorizontalAdapter(Context context, ArrayList<String> shoe_name, ArrayList<String> shoe_image, ArrayList<String> shoe_link, String[] shoe_prices) {
        this.context = context;
        this.shoe_image = shoe_image;
        this.shoe_name = shoe_name;
        this.shoe_link = shoe_link;
        this.shoe_price = shoe_prices;
    }

    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.new_arrival_item, viewGroup, false);
        return new HorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorizontalAdapter.ViewHolder holder, int position) {
        String title = shoe_name.get(position);
        holder.title.setText(title);

        Picasso.get().load(shoe_image.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return shoe_name.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;

        ViewHolder(final View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.imageView = itemView.findViewById(R.id.img);
        }
    }
}