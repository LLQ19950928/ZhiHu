package com.llq.zhihu;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/9/27.
 */

public class ZhiHuApplication extends Application {

    private static RequestQueue requestQueue;
    private static Context context;
    @Override
    public void onCreate() {

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        context = getApplicationContext();
        super.onCreate();
    }

    public static RequestQueue getRequestQueue() {

        return requestQueue;
    }

    public static Context getContext() {

        return context;
    }
}
