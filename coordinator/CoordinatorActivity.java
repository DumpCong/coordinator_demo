package com.xu.workwork.coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xu.workwork.R;

/**
 * MD CoordinatorLayout的小demo
 */
public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
    }

    public void doIntent(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                // 简单的与子View配合的显示隐藏
                intent.setClass(this, AppbarActivity.class);
                break;
            case R.id.button2:
                //AppBar渐变效果
                intent.setClass(this, CollaspingToolbarActivity.class);
                break;
            case R.id.button3:
                intent.setClass(this,CollaspingToolbarActivity.class);
                break;
        }
        startActivity(intent);
    }
}
