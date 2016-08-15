package jacksen.recyclerviewdemo.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;
import jacksen.recyclerviewdemo.common.ItemListener;
import jacksen.recyclerviewdemo.common.MyItemDecoration;

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

        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("item " + i);
        }

        adapter = new ListLayoutAdapter(this, data);
        adapter.setItemListener(new ItemListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


        recyclerView.setAdapter(adapter);

    }
}
