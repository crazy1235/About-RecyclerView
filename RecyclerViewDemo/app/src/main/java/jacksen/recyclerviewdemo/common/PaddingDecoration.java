package jacksen.recyclerviewdemo.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Adds padding between items in a RecyclerView with a vertical LinearLayout.
 */
public class PaddingDecoration extends RecyclerView.ItemDecoration {

    private final int padding;

    public PaddingDecoration(int padding) {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int layoutPosition = parent.getChildLayoutPosition(view);
        int topPadding = layoutPosition == 0 ? padding : padding / 2;
        int bottomPadding = layoutPosition == state.getItemCount() - 1 ? padding : padding / 2;
        outRect.set(0, topPadding, 0, bottomPadding);
    }
}
