package africa.apeiron.batafind.CATEGORIES;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import africa.apeiron.batafind.HOME.HomeAction;
import africa.apeiron.batafind.R;
/**
 * Created by Granson on 08-Apr-18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MasonryView> {

private Context myContext;

//All kids
        int[] kids_img = {R.drawable.for_men,R.drawable.lady,R.drawable.playing_kids};

        String[] list = {"Men","Ladies","Kids"};


public CategoryAdapter(Context context) {
        this.myContext = context;
        }

@Override
public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
        }

@Override
public void onBindViewHolder(MasonryView holder, final int position) {

        Picasso.get().load(kids_img[position]).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Toast.makeText(myContext,list[position],Toast.LENGTH_LONG).show();
            // Open to new Intent
            Intent I = new Intent(myContext, HomeAction.class);
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