package africa.apeiron.batafind.FRAGMENTS.GROUPED_CATEGORY;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Profile_Decorator extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public Profile_Decorator(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0){
            outRect.top = mSpace;
        }

        // Add top margin only for the sec item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 1){
            outRect.top = mSpace;
        }
    }
}