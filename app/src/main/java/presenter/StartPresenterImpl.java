package presenter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.llq.zhihu.ZhiHuApplication;

import java.util.logging.Logger;

import contract.TaskContract;
import model.StartImage;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by Administrator on 2016/9/28.
 */

public class StartPresenterImpl implements
        Presenter {

    private TaskContract.StartView startView;
    private RequestQueue mRequestQueue;

    public StartPresenterImpl(TaskContract.StartView startView) {

        this.startView = startView;
        startView.setPresenter(this);
    }

    @Override
    public void start() {

        handleInfo();
    }

    private void handleInfo() {

        mRequestQueue = ZhiHuApplication.getRequestQueue();
        StringRequest stringRequest = new StringRequest(Constant.IMG,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                startView.setBackground(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mRequestQueue.add(stringRequest);
    }
}
