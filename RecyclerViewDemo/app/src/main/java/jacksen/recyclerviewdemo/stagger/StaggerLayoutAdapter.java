package jacksen.recyclerviewdemo.stagger;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;

/**
 * Created by Admin on 2016/8/16.
 */

public class StaggerLayoutAdapter extends RecyclerView.Adapter<StaggerLayoutAdapter.ItemViewHolder> {


    private List<String> data;

    private List<Integer> heights = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public StaggerLayoutAdapter(Context context, List<String> data) {
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        for (int i = 0; i < data.size(); i++) {
            heights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
