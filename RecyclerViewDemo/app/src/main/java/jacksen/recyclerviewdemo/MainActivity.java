package jacksen.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import jacksen.recyclerviewdemo.grid.GridLayoutActivity;
import jacksen.recyclerviewdemo.list.ListLayoutActivity;
import jacksen.recyclerviewdemo.stagger.StaggerLayoutActivity;

public class MainActivity extends AppCompatActivity {

    private Button linearBtn, gridBtn, staggerBtn, loadMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearBtn = (Button) findViewById(R.id.linear_btn);
        gridBtn = (Button) findViewById(R.id.grid_btn);
        staggerBtn = (Button) findViewById(R.id.stagger_btn);
        loadMoreBtn = (Button) findViewById(R.id.load_more_btn);

        linearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListLayoutActivity.class));
            }
        });

        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GridLayoutActivity.class));
            }
        });

        staggerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StaggerLayoutActivity.class));
            }
        });

        loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
