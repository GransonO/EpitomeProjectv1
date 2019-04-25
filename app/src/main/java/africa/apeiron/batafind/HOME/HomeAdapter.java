package africa.apeiron.batafind.HOME;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import africa.apeiron.batafind.R;
/**
 * Created by Granson on 08-Apr-18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MasonryView> {

private Context myContext;
ArrayList<String> shoe_image;
ArrayList<String> shoe_name;
ArrayList<String> shoe_link;
String[] shoe_price;

public HomeAdapter(Context context, ArrayList<String> name, ArrayList<String> image, ArrayList<String> link, String[] Prices) {
    this.myContext = context;
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
public void onBindViewHolder(MasonryView holder, final int position) {

        Picasso.get().load(shoe_image.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(myContext,shoe_name.get(position),Toast.LENGTH_LONG);

            }
        });
        holder.textView.setText(shoe_name.get(position));
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