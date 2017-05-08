package com.xu.workwork;

import android.app.Application;
import android.content.Context;

/**
 * nnnn
 * Created by xu on 2017/4/10.
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        if (context == null) {
            context = new MyApplication();
        }
        return context;
    }
}
