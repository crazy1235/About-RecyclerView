package jacksen.recyclerviewdemo.stagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import jacksen.recyclerviewdemo.R;

public class StaggerLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<String> data;

    private StaggerLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger_layout);

        //
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // 设置布局管理器
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置item分割view
//        recyclerView.addItemDecoration(new GridItemDecoration(this));
        // ???
        recyclerView.setHasFixedSize(true);

        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }

        adapter = new StaggerLayoutAdapter(this, data);

        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
