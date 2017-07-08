package util;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.llq.zhihu.ZhiHuApplication;

import model.GsonRequest;

/**
 * Created by Administrator on 2016/9/30.
 */

public class HttpUtil {

     private static RequestQueue requestQueue;
     public static  void getInfoFromHttp(String url,
                                            final HttpHandler httpHandler, Class clazz) {
            requestQueue = ZhiHuApplication.getRequestQueue();
            GsonRequest gsonRequest = new GsonRequest(url, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }, new Response.Listener() {
                @Override
                public void onResponse(Object object) {

                    httpHandler.onFinish(object);
                }
            }, clazz);
            requestQueue.add(gsonRequest);
     }
}
