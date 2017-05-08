package com.xu.workwork.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xu.workwork.R;

import org.greenrobot.eventbus.EventBus;

public class EventTwoActivity extends AppCompatActivity {

    private TextView tv;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_two);
        initView();
    }

    private void initView() {
        tv = (TextView) this.findViewById(R.id.textView2);
        et = (EditText) this.findViewById(R.id.editText);
        tv.setText(this.toString());
    }

    public void postEvent(View v) {
        EventBus.getDefault().post(new MyEvent(et.getText().toString()));
    }
}
