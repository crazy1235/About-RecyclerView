package jacksen.recyclerviewdemo.empty;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author jacksen
 * @create_date 2017/3/29.
 * @desc
 */

public class RecyclerViewPlus extends RecyclerView {

    private View emptyView;

    public RecyclerViewPlus(Context context) {
        super(context);
    }

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewPlus(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * set empty view
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        showOrHideEmptyView();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(dataObserver);
        }
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(dataObserver);
        }

        showOrHideEmptyView();
    }

    AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            showOrHideEmptyView();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            showOrHideEmptyView();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            showOrHideEmptyView();
        }
    };

    private void showOrHideEmptyView() {
        if (emptyView != null && getAdapter() != null) {
            boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE); // empty view
            this.setVisibility(emptyViewVisible ? GONE : VISIBLE); // recycler view
        }
    }
}
