package llq.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button mBtnGetNet;
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";
    public static final String TAG = "MainActivity";
    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        //创建一个Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL).build();
        //创建一个代理类
        api = retrofit.create(Api.class);

        mBtnGetNet = (Button)findViewById(R.id.btn_getNet);
        mBtnGetNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResponseBody> call
                        = api.getZhiHuAnyTheme(2);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {

                        try {

                            String info = response.body().string();
                            Log.d(TAG, info);

                        }catch(Exception e) {
                          e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }
}
