package jacksen.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        List<HeroBean> list = new ArrayList<>();
        HeroBean heroBean = new HeroBean("1.jpg", "songjiang", "孝义黑三郎");
        list.add(heroBean);
        heroBean = new HeroBean("1.jpg", "songjiang", "孝义黑三郎");
        list.add(heroBean);
        heroBean = new HeroBean("2.jpg", "likui", "黑旋风");
        list.add(heroBean);
        heroBean = new HeroBean("3.jpg", "wusong", "武松打虎");
        list.add(heroBean);
        heroBean = new HeroBean("4.jpg", "songjiang", "孝义黑三郎");
        list.add(heroBean);
        heroBean = new HeroBean("5.jpg", "linchong", "八十万禁军教头");
        list.add(heroBean);

        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        linearLayoutManager.setRecycleChildrenOnDetach(true);
//        RecyclerView.RecycledViewPool pool = recyclerView.getRecycledViewPool();


        itemTouchHelper = new ItemTouchHelper(new MyCallBack());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.addItems(list);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_item) {
            HeroBean heroBean = new HeroBean("11.jpg", "aaaaaaaaa", "母夜叉");
            adapter.addItem(heroBean);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
