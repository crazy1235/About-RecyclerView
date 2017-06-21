package jacksen.recyclerviewdemo.empty;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.list.ListItemDecoration;
import jacksen.recyclerviewdemo.list.ListLayoutAdapter;

/**
 * add empty view
 *
 * @author jacksen
 */
public class EmptyViewActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    private View emptyView;

    private RecyclerViewPlus recyclerView;

    private ListLayoutAdapter adapter;

    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_view);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        recyclerView = (RecyclerViewPlus) findViewById(R.id.recycler_view);

        emptyView = findViewById(R.id.empty_view);

        initView();
    }


    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        data = new ArrayList<>();
        adapter = new ListLayoutAdapter(this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.setEmptyView(emptyView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                adapter.addItem(0, "天使爱美丽~");
                break;
            case R.id.action_remove:
                adapter.removeItem(0);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
