package model;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/9/27.
 */

public class GsonRequest<T> extends Request<T> {

    private Class clazz;
    private Response.Listener listener;
    private Gson gson;

    public GsonRequest(int method, String url, Response.ErrorListener errorListener,
                       Response.Listener listener, Class clazz) {
        super(method, url, errorListener);
        this.listener = listener;
        this.clazz = clazz;
        gson = new Gson();
    }

    public GsonRequest(String url, Response.ErrorListener errorListener,
                       Response.Listener listener, Class clazz) {

        this(Method.GET, url, errorListener, listener, clazz);
    }

    @Override
    protected void deliverResponse(T response) {

         listener.onResponse(response);
    }

    @Override
    protected Response parseNetworkResponse(
            NetworkResponse networkResponse) {

        try {
            String jsonString = new String(networkResponse.data,
                    "utf-8");
            return Response.success(gson.fromJson(jsonString, clazz),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }
}
