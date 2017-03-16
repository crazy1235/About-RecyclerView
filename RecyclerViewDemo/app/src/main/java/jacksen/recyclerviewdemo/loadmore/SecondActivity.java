package jacksen.recyclerviewdemo.loadmore;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.list.ListItemDecoration;
import jacksen.recyclerviewdemo.list.ListLayoutAdapter;

/**
 * load more
 *
 * @author jacksen
 */
public class SecondActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    private List<String> data;

    private ListLayoutAdapter adapter;

    private int index = 20;
    private HeaderViewRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        refreshLayout.setColorSchemeResources(R.color.color_swipe_1, R.color.color_swipe_2, R.color.color_swipe_3, R.color.color_swipe_4);

        refreshLayout.setOnRefreshListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        data = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            data.add("item " + i);
        }
        adapter = new ListLayoutAdapter(this, data);

        recyclerAdapter = new HeaderViewRecyclerAdapter(adapter);
        recyclerAdapter.setAdapter(adapter);
        recyclerAdapter.addFooterView(createLoadMoreView());

        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new LoadMoreListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMore();
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    /**
     *
     */
    private View createLoadMoreView() {
        return LayoutInflater.from(this).inflate(R.layout.view_load_more, recyclerView, false);
    }

    private void loadMore() {
        List<String> tempList = new ArrayList<>();
        for (int i = index; i < index + 5; i++) {
            tempList.add("item " + i);
        }
        data.addAll(tempList);
        recyclerAdapter.notifyDataSetChanged();
        index += 5;
    }
}
