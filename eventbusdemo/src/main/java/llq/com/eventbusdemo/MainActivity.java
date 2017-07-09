package llq.com.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import message.ActivityMessage;

public class MainActivity extends AppCompatActivity {

    private Button mStartActivityButton;
    private TextView mContentTextView;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView()
    {
        mStartActivityButton = (Button)findViewById(R.id.btn_activity_start);
        mButton = (Button)findViewById(R.id.button);
        mContentTextView = (TextView)findViewById(R.id.tv_content);
        mStartActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        //粘性事件
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //注册事件
                EventBus.getDefault().register(MainActivity.this);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(ActivityMessage as)
    {
        mContentTextView.setText(as.getMessage());
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
