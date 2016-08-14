package jacksen.recyclerviewdemo;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 2016/6/15.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private SortedList<HeroBean> sortedList;
    private LayoutInflater layoutInflater;

    public MainAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        sortedList = new SortedList<HeroBean>(HeroBean.class, new HeroListCallback(this));
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_linear, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.nameTv.setText(sortedList.get(position).getName());
        holder.introTv.setText(sortedList.get(position).getIntroduce());
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    /**
     * @param items
     */
    public void addItems(List<HeroBean> items) {
        sortedList.beginBatchedUpdates();
        sortedList.addAll(items);
        sortedList.endBatchedUpdates();
    }

    public void addItem(HeroBean item){
        sortedList.add(item);
    }


    static class HeroListCallback extends SortedListAdapterCallback<HeroBean> {

        /**
         * Creates a {@link SortedList.Callback} that will forward data change events to the provided
         * Adapter.
         *
         * @param adapter The Adapter instance which should receive events from the SortedList.
         */
        public HeroListCallback(RecyclerView.Adapter adapter) {
            super(adapter);
        }

        @Override
        public int compare(HeroBean o1, HeroBean o2) {
            int comp = o1.getName().compareTo(o2.getName());
            if (comp != 0) {
                return comp;
            }
            return 0;
        }

        @Override
        public boolean areContentsTheSame(HeroBean oldItem, HeroBean newItem) {//判断新的item与旧的item是否发生改变
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areItemsTheSame(HeroBean item1, HeroBean item2) {
            return item1.getName().equals(item2.getName());
        }
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        TextView introTv;

        MainViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.item_name_tv);
            introTv = (TextView) itemView.findViewById(R.id.item_intro_tv);
        }
    }
}
