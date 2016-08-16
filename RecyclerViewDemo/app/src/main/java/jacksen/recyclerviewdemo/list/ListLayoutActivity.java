package jacksen.recyclerviewdemo.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.ItemListener;
import jacksen.recyclerviewdemo.common.MyItemDecoration;
import jacksen.recyclerviewdemo.common.MySimpleCallback;

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
        recyclerView.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL));
        // ???
        recyclerView.setHasFixedSize(true);

        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
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

        MySimpleCallback callback = new MySimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);

        callback.setCallbackListener(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        // 绑定
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
}
