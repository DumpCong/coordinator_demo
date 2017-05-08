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

public class AppbarActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7"
            , "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"
            , "18", "19", "20", "21", "22", "23", "24", "25", "26", "27"
            , "28", "29", "30"};
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);
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
