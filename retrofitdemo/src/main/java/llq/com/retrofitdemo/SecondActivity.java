package llq.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    private Button mBtnGetTheme;
    private TextView mTvShowTheme;
    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView()
    {
        mBtnGetTheme = (Button)findViewById(R.id.btn_getTheme);
        mTvShowTheme = (TextView)findViewById(R.id.tv_theme);

         Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://news-at.zhihu.com/api/4/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
          api = retrofit.create(Api.class);

        mBtnGetTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Call<Other> otherCall =  api.getZhiHuWithGson();
               otherCall.enqueue(new Callback<Other>() {
                   @Override
                   public void onResponse(Call<Other> call, Response<Other> response) {

                         mTvShowTheme.setText(
                                 response.body().getOtherNewses().get(0).getName());
                   }

                   @Override
                   public void onFailure(Call<Other> call, Throwable t) {

                   }
               });
            }
        });
    }
}
