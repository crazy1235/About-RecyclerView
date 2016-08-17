package jacksen.recyclerviewdemo.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by Admin on 2016/8/14.
 */

public class MyCallBack extends ItemTouchHelper.Callback {


    private CallbackListener callbackListener;

    private int dragBgColor = Color.parseColor("#f5f5f5");

    public MyCallBack() {

    }

    public void setCallbackListener(CallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    // 是否可以滑动删除
    // 返回true表示支持左右滑动
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    // 是否可以长按拖动
    // 返回true表示支持长按拖拽
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.d("MyCallBack", "getMovementFlags");
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        return 0;
    }

    // 拖动时，通知adapter调整数据
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d("MyCallBack", "onMove");
        callbackListener.onMove(recyclerView, viewHolder, target);
        return true;
    }

    // 滑动删除相应操作，主要用来通知adapter移除相关item
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.d("MyCallBack", "onSwiped");
        callbackListener.onSwiped(viewHolder, direction);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d("MyCallBack", "onChildDraw -- dX -- dY -- actionState -- isCurrentlyActive>>>" + dX + "  " + dY + "  " + actionState + "  " + isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {// 滑动的是改变item的透明度
            float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            Log.d("MyCallBack", "alpha:" + alpha);
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX); // 设置X轴方向的偏移
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d("MyCallBack", "onChildDrawOver -- dX -- dY -- actionState -- isCurrentlyActive>>>" + dX + "  " + dY + "  " + actionState + "  " + isCurrentlyActive);
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    // 重写此方法可以添加一些选中的动画逻辑
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.d("MyCallBack", "onSelectedChanged -- actionState:" + actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(dragBgColor);
        }
    }

    // 动画结束时，重写此方法恢复item的初始状态
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.d("MyCallBack", "clearView");
//        viewHolder.itemView.setAlpha(1.0f);
//        viewHolder.itemView.setBackgroundColor(Color.GREEN);
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }
}
