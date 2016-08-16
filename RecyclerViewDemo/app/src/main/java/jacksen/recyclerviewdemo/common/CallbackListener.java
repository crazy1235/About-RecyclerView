package jacksen.recyclerviewdemo.common;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ys on 2016/8/16.
 */

public interface CallbackListener {

    void onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);


    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
}
