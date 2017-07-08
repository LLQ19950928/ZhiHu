package model;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.llq.zhihu.ZhiHuApplication;

import util.HttpHandler;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ThemeModel {

    private RequestQueue requestQueue;

    public void getInfoFromHttp(String url, final HttpHandler httpHandler) {

         requestQueue = ZhiHuApplication.getRequestQueue();
         GsonRequest gsonRequest = new GsonRequest(url, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError volleyError) {

             }
         }, new Response.Listener<Theme>() {
             @Override
             public void onResponse(Theme theme) {

                 httpHandler.onFinish(theme);
             }
         }, Theme.class);

        requestQueue.add(gsonRequest);
    }
}
