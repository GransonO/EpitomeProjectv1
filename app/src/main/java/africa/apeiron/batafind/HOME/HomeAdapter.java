package africa.apeiron.batafind.HOME;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import africa.apeiron.batafind.GROUPED_CATEGORY.GroupedCategoryAction;
import africa.apeiron.batafind.R;
/**
 * Created by Granson on 08-Apr-18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MasonryView> {

private Context myContext;

//Images
        //int[] cat_img = {R.drawable.for_men,R.drawable.lady,R.drawable.playing_kids};

        //String[] list = {"Men","Ladies","Kids"};

    int[] cat_img = { R.drawable.for_men,R.drawable.safari,R.drawable.leather_shoes,
            R.drawable.bata_casual,R.drawable.beach,R.drawable.weinbrenner,R.drawable.tommy_men,
            R.drawable.bata_office_man,R.drawable.north_men,R.drawable.patapata,R.drawable.sports
    };

    String[] list = {"All Men", "Safari","Official","Casual","Sandals",
            "Weinbrenner","Tommy Men","Bata collection","North Star",
            "Patapata","Sports"
    };


public HomeAdapter(Context context) {
        this.myContext = context;
        }

@Override
public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
        }

@Override
public void onBindViewHolder(MasonryView holder, final int position) {

        Picasso.get().load(cat_img[position]).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                Intent I = new Intent(myContext, GroupedCategoryAction.class);
                I.putExtra("Name",list[position]);
                myContext.startActivity(I);

            }
        });
        holder.textView.setText(list[position]);
        }

@Override
public int getItemCount() {
        return list.length;
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