package view.activity;


import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.llq.zhihu.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import model.Other;
import util.FragmentUtil;
import view.fragment.FirstPageFragment;
import view.fragment.ThemeContentFragment;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @BindView(R.id.tool_bar)
    Toolbar toolbar; 
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ActionBarDrawerToggle toggle;
    private FirstPageFragment firstPageFragment;
    private ThemeContentFragment themeContentFragment;
    private boolean isFirstPage = true;
    private int id;
    private long oldTime;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

       //注册事件
       EventBus.getDefault().register(this);

       mode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, 
                toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        firstPageFragment = new FirstPageFragment();
        FragmentUtil.transaction(this, firstPageFragment);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary, R.color.colorAccent);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                themeContentFragment = new ThemeContentFragment();
                firstPageFragment = new FirstPageFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("theme_id", id);
                themeContentFragment.setArguments(bundle);
                FragmentUtil.transaction(MainActivity.this,
                        isFirstPage ? firstPageFragment:
                      themeContentFragment);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main_1, menu);
        
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch (item.getItemId()) {
            case  R.id.item_settings:

                break;
            case  R.id.item_night_mode:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeDrawer() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void returnFirstPage() {

        firstPageFragment = new FirstPageFragment();
        FragmentUtil.transaction(this, firstPageFragment);
        closeDrawer();
        toolbar.setTitle("首页");
        isFirstPage = true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNameAndId(Other other) {

        toolbar.setTitle(other.getName());
        this.id = other.getId();
        isFirstPage = false;
        themeContentFragment = new ThemeContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("theme_id", id);
        themeContentFragment.setArguments(bundle);
        FragmentUtil.transaction(this, themeContentFragment);
        closeDrawer();
    }

    @OnClick({R.id.fab})
    public void onClicked(View view) {

        switch (view.getId()) {
            case  R.id.fab:
                if (isFirstPage)
                    firstPageFragment.returnTop();
                else
                    themeContentFragment.returnTop();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        if(currentTime - oldTime >= 2000) {
            Snackbar snackbar = Snackbar.make(swipeRefreshLayout, "再按一次退出",
                    Snackbar.LENGTH_SHORT);
            snackbar.getView()
                    .setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
            oldTime = currentTime;
        } else {

            finish();
        }

    }

    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
