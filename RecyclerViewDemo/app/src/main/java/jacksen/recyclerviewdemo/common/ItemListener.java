package jacksen.recyclerviewdemo.common;

import android.view.View;

/**
 * Item监听
 * Created by ys on 2016/8/15.
 */

public interface ItemListener {

    /**
     * 点击事件
     *
     * @param view
     * @param position
     */
    void onItemClick(View view, int position);

    /**
     * 长按事件
     *
     * @param view
     * @param position
     */
    void onItemLongClick(View view, int position);

}
