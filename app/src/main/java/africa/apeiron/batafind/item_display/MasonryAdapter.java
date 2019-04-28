package africa.apeiron.batafind.item_display;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import africa.apeiron.batafind.item_details.SelectedItem;
import africa.apeiron.batafind.R;

/**
 * Created by Granson on 25-Mar-18.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private Context context;
    MasonryView holder;

    ArrayList<String> shoe_image;
    ArrayList<String> shoe_name;
    ArrayList<String> shoe_link;
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
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        this.holder = holder;

        Picasso.get().load(shoe_image.get(position)).into(holder.imageView);
        final int holderPosition = holder.getAdapterPosition();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(context, SelectedItem.class);
                I.putExtra("shoe_image",shoe_image.get(holderPosition));
                I.putExtra("shoe_name",shoe_name.get(holderPosition));
                I.putExtra("shoe_link",shoe_link.get(holderPosition));
                I.putExtra("shoe_price",shoe_price[holderPosition]);
                context.startActivity(I);
            }
        });

        holder.textView.setText(shoe_name.get(position));
        holder.textView.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return shoe_name.size();
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
}
