package africa.apeiron.batafind.HOME;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Granson on 08-Apr-18.
 */

public class HomeClass extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public HomeClass(int space) {
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