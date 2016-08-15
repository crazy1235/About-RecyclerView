package jacksen.recyclerviewdemo.list;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.ItemListener;

/**
 * LinearLayoutManager适配器
 * <p>
 * Created by ys on 2016/8/15.
 */

public class ListLayoutAdapter extends RecyclerView.Adapter<ListLayoutAdapter.ItemViewHolder> {

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
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Log.d("ListLayoutAdapter", "onBindViewHolder");
        holder.textView.setText(data.get(position));
        Log.d("ListLayoutAdapter", holder.toString());
    }

    @Override
    public int getItemCount() {
        Log.d("ListLayoutAdapter", "getItemCount");
        return data.size();
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
