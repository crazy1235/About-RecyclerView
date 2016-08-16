package jacksen.recyclerviewdemo.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.CallbackListener;

/**
 * adapter for gridlayoutmanager
 * <p>
 * Created by ys on 2016/8/16.
 */

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.ItemViewHolder> implements CallbackListener {

    private List<String> data;

    private LayoutInflater layoutInflater;

    public GridLayoutAdapter(Context context, List<String> data) {
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new GridLayoutAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPos = viewHolder.getAdapterPosition();
        int toPos = target.getAdapterPosition();
        if (fromPos == toPos) {
            return;
        }
        if (fromPos < toPos) { // 注意对data进行重新排序
            for (int i = fromPos; i < toPos; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPos; i > toPos; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPos, toPos);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int targetPos = viewHolder.getAdapterPosition();
        notifyItemRemoved(targetPos);
        data.remove(targetPos);
    }

    /**
     * 向指定位置添加元素
     *
     * @param position
     * @param value
     */
    public void addItem(int position, String value) {
        if (position > data.size()) {
            position = data.size();
        }
        if (position < 0) {
            position = 0;
        }
        data.add(position, value);

        // 使用notifyItemInserted / notifyItemRemoved 有动画效果
        // 使用nofityDataSetChanged 没有动画效果
        notifyItemInserted(position);
    }


    /**
     * @param position
     */
    public String removeItem(int position) {
        if (position < 0 && position > data.size() - 1) {
            return null;
        }
        String value = data.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
