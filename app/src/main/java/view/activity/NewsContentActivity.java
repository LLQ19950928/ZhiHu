package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.llq.zhihu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import contract.NewsContentView;
import model.ExtraStory;
import presenter.NewsContentPresenterImpl;
import presenter.Presenter;
import util.GlideUtil;

/**
 * Created by lenovo on 2016/10/10.
 */

public class NewsContentActivity extends AppCompatActivity
    implements NewsContentView {

    @BindView(R.id.background_imageView)
    ImageView background_imageView;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.share_fab)
    FloatingActionButton share_fab;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    private Menu menu;
    public static final String TAG = "赞的数量";
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);
        initView();
        super.onCreate(savedInstanceState);
    }

    private void initView() {

        intent = new Intent(this, CommentActivity.class);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web_view.getSettings().setDatabaseEnabled(true);
        web_view.getSettings().setAppCacheEnabled(true);
        web_view.getSettings().setDomStorageEnabled(true);
        new NewsContentPresenterImpl(this);

    }

    @Override
    public void setImageBackground(String url) {

        GlideUtil.setImageBackground(this, url, background_imageView);
    }

    @Override
    public void setTitleText(String text) {

        Log.d(TAG, "setTitleText: " + text);
        collapsingToolbarLayout.setTitle(text);
    }

    @Override
    public void setWebViewContent(String body) {

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        web_view.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_2, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case  R.id.item_chat:
                intent.putExtra("newsID", getIntent().getIntExtra("newsID", -1));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCountOfComments(String countOfComments) {

        menu.findItem(R.id.item_chat).setTitle(countOfComments);
    }

    @Override
    public void setPresenter(Presenter presenter) {

        presenter.start();
    }

    @Override
    public int getNewsId() {

        return getIntent().getIntExtra("newsID", -1);
    }

    @Override
    public void setCountOfThumbs(String countOfThumbs) {

        menu.findItem(R.id.item_thumb).setTitle(countOfThumbs).setShowAsActionFlags(
                MenuItem.SHOW_AS_ACTION_WITH_TEXT | MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public void setExtraStory(ExtraStory extraStory) {

        intent.putExtra("extraStory", extraStory);
    }

    @Override
    public void setShareUrl(final String shareUrl) {

        share_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "分享至"));
            }
        });
    }
}
