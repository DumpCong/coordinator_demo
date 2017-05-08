package com.xu.workwork.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xu.workwork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOneActivity extends AppCompatActivity {

    public String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7"
            , "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"
            , "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"
            , "28", "29", "30"};
    public List<String> mData;
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_one);
        initData();

    }

    private void initData() {
        mData = new ArrayList<>(Arrays.asList(strs));
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        //线性布局 第二个参数 滚动方向 第三个参数 是否倒序
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this,
                OrientationHelper.VERTICAL, false);
        //网格布局 第二个参数 每行的item个数
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false);
        //瀑布流布局
        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);//设置布局
        mRecyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        mAdapter = new TestAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
        //给条目设置点击事件 不设置点击事件的话 selector是无效果的
        mAdapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                mData.add(position, "新的条目" + position);
                mAdapter.addItem(position);
            }

            @Override
            public void onLongClick(View itemView, int position) {
                mData.remove(position);
                mAdapter.removeItem(position);
            }

            @Override
            public void onTextViewClick(View textView, int position) {
                Toast.makeText(getApplicationContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 3, "添加");
        menu.add(0, 2, 3, "移除");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mData.add(mData.size() - 3, "new");
                mAdapter.addItem(mData.size() - 4);

                break;
            case 2:
                mAdapter.removeItem(mData.size() - 1);
                mData.remove(mData.size() - 1);
                break;
        }
        return true;
    }
}
