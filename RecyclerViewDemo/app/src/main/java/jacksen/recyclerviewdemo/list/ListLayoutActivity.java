package jacksen.recyclerviewdemo.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.ItemListener;
import jacksen.recyclerviewdemo.common.ItemOnTouchListener;
import jacksen.recyclerviewdemo.common.MyCallBack;

/**
 * LinearLayoutManager
 *
 * @author ys
 */
public class ListLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> data;

    private ListLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // 设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置item分割view
        recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        // ???
        recyclerView.setHasFixedSize(true);

        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }

        adapter = new ListLayoutAdapter(this, data);
        adapter.setItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ListLayoutActivity.this, "第 " + position + " 个item被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ListLayoutActivity.this, "第 " + position + " 个item被长按了", Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView.setAdapter(adapter);

        /*MySimpleCallback callback = new MySimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);

        callback.setCallbackListener(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);*/

        MyCallBack callback = new MyCallBack();

        callback.setCallbackListener(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);


        // 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // 添加点击事件
        recyclerView.addOnItemTouchListener(new ItemOnTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                super.onItemClick(viewHolder);
                Toast.makeText(ListLayoutActivity.this, viewHolder.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {
                super.onItemLongClick(viewHolder);
                Log.d("ListLayoutActivity", viewHolder.getOldPosition() + "--" + viewHolder.getLayoutPosition() + "--" + viewHolder.getAdapterPosition()
                        + "--" + viewHolder.getItemId() + "--" + viewHolder.getItemViewType());
            }
        });

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
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.HORIZONTAL));
                break;
            case R.id.item_vertical:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
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
}
