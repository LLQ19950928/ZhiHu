package llq.com.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import message.Weather;
import message.WeatherInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetActivity extends AppCompatActivity {

    private Button mWeatherInfoButton;
    private TextView mWeatherInfoTextView;
    private OkHttpClient mOkHttpClient;
    public static final String URL_WEATHER = "http://guolin.tech/api/china";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        initView();
    }

    private void initView()
    {
        mOkHttpClient = new OkHttpClient();

        mWeatherInfoButton = (Button)findViewById(R.id.btn_weatherInfo_get);
        mWeatherInfoTextView = (TextView)findViewById(R.id.tv_weatherInfo);
        //注册事件
        EventBus.getDefault().register(this);
        mWeatherInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 mOkHttpClient.newCall(new Request.Builder()
                         .url(URL_WEATHER).build())
                         .enqueue(new Callback() {
                             @Override
                             public void onFailure(Call call, IOException e) {

                             }

                             @Override
                             public void onResponse(Call call,
                                                    Response response) throws IOException {

                                 String weatherInfo = response.body().string();
                                 Log.d("Hee", weatherInfo);
                                 try {

                                     JSONArray jsonArray = new JSONArray(weatherInfo);
                                     JSONObject info = jsonArray.getJSONObject(0);
                                     WeatherInfo w = new WeatherInfo(
                                             info.getString("name"), info.getInt("id"));
                                     EventBus.getDefault().post(w);
                                 }catch(Exception e) {
                                   e.printStackTrace();
                                 }

                             }
                         });
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initTextView(WeatherInfo info)
    {
        mWeatherInfoTextView.setText(info.getName());
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
