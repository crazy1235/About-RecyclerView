package jacksen.recyclerviewdemo.common;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * item触摸监听
 * click & longClick
 * <p>
 * Created by ys on 2016/8/16.
 */

public class ItemOnTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat gestureDetectorCompat;

    private RecyclerView recyclerView;

    public ItemOnTouchListener(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    /**
     *
     */
    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                onItemClick(recyclerView.getChildViewHolder(child));
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                onItemLongClick(recyclerView.getChildViewHolder(child));
            }
        }
    }

    /**
     * @param viewHolder
     */
    public void onItemClick(RecyclerView.ViewHolder viewHolder) {

    }

    /**
     * @param viewHolder
     */
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {

    }
}
