package jacksen.recyclerviewdemo.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Admin
 * @create_date 2017/3/16.
 * @desc 描述
 */

public abstract class LoadMoreListener extends RecyclerView.OnScrollListener {

    private int currentPage = 1;

    private boolean loading = true;

    private int previousTotalCount = 0;

    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    private LinearLayoutManager linearLayoutManager;

    public LoadMoreListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public abstract void onLoadMore(int currentPage);

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotalCount) {
                loading = false;
                previousTotalCount = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }

    }
}
