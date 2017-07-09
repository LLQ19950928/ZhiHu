package llq.com.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import message.ActivityMessage;

public class SecondActivity extends AppCompatActivity {

    private Button mFinishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView()
    {
        mFinishButton = (Button)findViewById(R.id.btn_activity_finish);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(
                        new ActivityMessage("我是第二个Activity"));
                finish();
            }
        });
    }
}
