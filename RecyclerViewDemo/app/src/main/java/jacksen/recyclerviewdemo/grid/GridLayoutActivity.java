package jacksen.recyclerviewdemo.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.MyCallBack;

/**
 * GridLayoutManager
 *
 * @author ys
 */
public class GridLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> data;

    private GridLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // 设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置item分割view
        recyclerView.addItemDecoration(new GridItemDecoration(this));
        // ???
        recyclerView.setHasFixedSize(true);

        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item " + i);
        }

        adapter = new GridLayoutAdapter(this, data);

        recyclerView.setAdapter(adapter);

        MyCallBack callback = new MyCallBack();

        callback.setCallbackListener(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        // 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_horizontal:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false));
                break;
            case R.id.item_vertical:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                break;
            case R.id.item_add_first:
                adapter.addItem(0, "first item");
                recyclerView.smoothScrollToPosition(0);
                break;
            case R.id.item_add_last:
                adapter.addItem(adapter.getItemCount(), "last item");
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
                break;
            case R.id.item_remove_first:
                adapter.removeItem(0);
                recyclerView.smoothScrollToPosition(0);
                break;
            case R.id.item_remove_last:
                adapter.removeItem(adapter.getItemCount() - 1);
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
