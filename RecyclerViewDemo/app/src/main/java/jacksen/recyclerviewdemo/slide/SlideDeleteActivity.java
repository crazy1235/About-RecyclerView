package jacksen.recyclerviewdemo.slide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jacksen.recyclerviewdemo.R;

/**
 * 侧滑删除
 *
 * @author ys
 */
public class SlideDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delete);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
