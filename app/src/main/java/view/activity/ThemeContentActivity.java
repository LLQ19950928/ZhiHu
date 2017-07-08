package view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.llq.zhihu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import contract.ContentView;
import model.ExtraStory;
import presenter.Presenter;
import presenter.ThemeNewsPresenterImpl;

/**
 * Created by lenovo on 2016/10/10.
 */

public class ThemeContentActivity extends AppCompatActivity
        implements ContentView {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.web_view)
    WebView mWebView;
    private Menu menu;
    private Intent intent;
    private String shareUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_theme_content);
        ButterKnife.bind(this);
        initView();
        super.onCreate(savedInstanceState);
    }

    private void initView() {

        intent = new Intent(this, CommentActivity.class);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        new ThemeNewsPresenterImpl(this);
    }


    @Override
    public void setWebViewContent(String body) {

        String css = "<link rel=\"stylesheet\" href=\"" +
                "file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + body + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_3, menu);
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
            case  R.id.item_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(shareIntent, "分享至"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCountOfComments(String countOfComments) {

        menu.findItem(R.id.item_chat).setTitle(countOfComments);
    }

    @Override
    public int getNewsId() {

        return getIntent().getIntExtra("newsID", -1);
    }

    @Override
    public void setCountOfThumbs(String countOfThumbs) {

        menu.findItem(R.id.item_thumb).setTitle(countOfThumbs);
    }


    @Override
    public void setPresenter(Presenter presenter) {

        presenter.start();
    }

    @Override
    public void setExtraStory(ExtraStory extraStory) {

        intent.putExtra("extraStory", extraStory);
    }

    @Override
    public void setShareUrl(String shareUrl) {

        this.shareUrl = shareUrl;
    }
}
