package jacksen.recyclerviewdemo.common;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Admin on 2016/8/14.
 */

public class MyCallBack extends ItemTouchHelper.Callback {


    // 是否可以滑动删除
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    // 是否可以长按拖动
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    // 拖动时，通知adapter调整数据
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    // 滑动删除相应操作，主要用来通知adapter移除相关item
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
