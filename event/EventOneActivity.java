package com.xu.workwork.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.xu.workwork.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 测试EventBusActivity
 */
public class EventOneActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_one);
        tv = (TextView) findViewById(R.id.textView);
        tv.setText(this.toString());
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventOneActivity.this, EventTwoActivity.class));
            }
        });
        //注册 EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    //绑定消息监听  收到的事件会在主线程中运行 可调用Event中的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMyEvent(MyEvent messageEvent) {
        //修改textView内容
        tv.setText(messageEvent.getMessage());
    }
}
