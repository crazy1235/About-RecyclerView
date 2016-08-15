package jacksen.recyclerviewdemo.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import jacksen.recyclerviewdemo.R;

/**
 * Created by ys on 2016/8/15.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;

    private final static int DEFAULT_ORIENTATION = LinearLayoutManager.VERTICAL;

    private int orientation = DEFAULT_ORIENTATION;


    public MyItemDecoration(Context context, int orientation) {
        if (orientation == LinearLayoutManager.VERTICAL || orientation == LinearLayoutManager.HORIZONTAL) {
            this.orientation = orientation;
        } else {
            this.orientation = DEFAULT_ORIENTATION;
        }
        drawable = ContextCompat.getDrawable(context, R.drawable.divider);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            onDrawHorizontal(c, parent);
        } else {
            onDrawVertical(c, parent);
        }

    }

    /**
     * @param c
     * @param parent
     */
    private void onDrawHorizontal(Canvas c, RecyclerView parent) {

    }


    /**
     * @param c
     * @param parent
     */
    private void onDrawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top;
        int bottom;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            top = child.getBottom() + params.bottomMargin;
            bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
