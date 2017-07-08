package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.llq.zhihu.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.Presenter;
import presenter.StartPresenterImpl;
import contract.TaskContract;

/**
 * Created by Administrator on 2016/9/28.
 */

public class StartActivity extends AppCompatActivity
        implements TaskContract.StartView {

    @BindView(R.id.image_view)
    ImageView startImageView;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        new StartPresenterImpl(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setBackground(String url) {

        Logger.d(url);
        Glide.with(this).load(url).into(startImageView);
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public void setPresenter(Presenter presenter) {

         presenter.start();
    }


}
