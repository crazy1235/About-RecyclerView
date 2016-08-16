package jacksen.recyclerviewdemo.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.CallbackListener;
import jacksen.recyclerviewdemo.common.ItemListener;

/**
 * LinearLayoutManager适配器
 * <p>
 * Created by ys on 2016/8/15.
 */

public class ListLayoutAdapter extends RecyclerView.Adapter<ListLayoutAdapter.ItemViewHolder> implements CallbackListener {

    private List<String> data;

    private ItemListener itemListener;

    private LayoutInflater layoutInflater;

    public ListLayoutAdapter(Context context, List<String> data) {
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("ListLayoutAdapter", "onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        Log.d("ListLayoutAdapter", "onBindViewHolder");
        holder.textView.setText(data.get(position));
        Log.d("ListLayoutAdapter", holder.toString());

        // adapter中设置item点击事件监听
        if (itemListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if (!holder.itemView.hasOnClickListeners()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemListener.onItemClick(view, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        itemListener.onItemLongClick(view, position);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        Log.d("ListLayoutAdapter", "getItemCount");
        return data.size();
    }

    @Override
    public void onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPos = viewHolder.getAdapterPosition();
        int toPos = target.getAdapterPosition();
        if (fromPos == toPos) {
            return;
        }
        if (fromPos < toPos) {
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


    /**
     *
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

}
