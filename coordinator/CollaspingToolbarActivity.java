package com.xu.workwork.coordinator;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xu.workwork.R;
import com.xu.workwork.recycler.MyDecoration;
import com.xu.workwork.recycler.TestAdapter;

import java.util.Arrays;

public class CollaspingToolbarActivity extends AppCompatActivity {

    /**
     * CollapsingToolbarLayout
     * 继承于FrameLayout。
     * <p/>
     * <p/>
     * title
     * 标题，布局展开时放大显示在图片底部，布局折叠时缩小显示在Toolbar左侧。注意，没有设置这个属性时，默认使用Toolbar的标题；
     * <p/>
     * statusBarScrim
     * 顶部视图折叠状态下，状态栏的遮罩色。通常这样设置：app:statusBarScrim="?attr/colorPrimaryDark"，即style样式中定义的沉浸式状态栏颜色。这个属性要和getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);（支持API19及以上版本，位于setContentView语句前面）一起使用，使顶部视图展开时图片能够延伸到状态栏位置显示，如效果图中所示；
     * <p/>
     * contentScrim
     * 内容遮罩，上下滚动时图片上面显示和隐藏的遮罩色，Toolbar位置的的背景色；通常这样设置：app:contentScrim="?attr/colorPrimary"，即显示为Toolbar颜色，应用的主题色；
     * <p/>
     * layout_collapseMode
     * 折叠模式，设置其他控件滚动时自身的交互行为，有两种取值：parallax，折叠视差效果，比如上述效果图中的图片；pin，固定别针效果，比如上图中的Toolbar；
     * <p/>
     * layout_collapseParallaxMultiplier
     * 不折叠视差系数，配合parallax模式使用，取值有点类似alpha（不透明度），在0.0 ～ 1.0之间，默认值为0.5。当设置为1.0，滚动列表时图片不会折叠移动；
     */
    private RecyclerView recyclerView;
    public String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7"
            , "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"
            , "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"
            , "28", "29", "30"};
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collasping_toolbar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) this.findViewById(R.id.recycler_test);
        TestAdapter adapter = new TestAdapter(this, Arrays.asList(strs));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        scrollView = (NestedScrollView) this.findViewById(R.id.scrollView);
        scrollView.smoothScrollTo(0, 0);
    }
}
