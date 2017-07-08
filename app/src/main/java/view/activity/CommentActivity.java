package view.activity;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.llq.zhihu.R;

import java.util.ArrayList;
import java.util.List;

import adapter.CommentAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.CommentView;
import model.Comment;
import model.ExtraStory;
import presenter.CommentPresenterImpl;
import presenter.Presenter;

/**
 * Created by lenovo on 2016/10/14.
 */

public class CommentActivity extends ExpandableListActivity
        implements CommentView {

    @BindView(android.R.id.list)
    ExpandableListView mExpandableListView;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    private CommentAdapter mCommentAdapter;
    private List<List<Comment>> mCommentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initView();
        super.onCreate(savedInstanceState);
    }

    private void initView() {

        int width = getWindowManager().getDefaultDisplay().getWidth();
        mExpandableListView.setIndicatorBounds(width-40, width-10);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 finish();
             }
         });
        ExtraStory extraStory = (ExtraStory)getIntent(
        ).getSerializableExtra("extraStory");
        mToolbar.setTitle(extraStory.getComments() + "条评论");
        new CommentPresenterImpl(this);

        mCommentAdapter = new CommentAdapter(this, mCommentList,
                new String[]{
                extraStory.getLong_comments() + "条长评"
        , extraStory.getShort_comments() + "条短评"});

    }

    @Override
    public int getNewsId() {

        return getIntent().getIntExtra("newsID", -1);
    }

    @Override
    public void setPresenter(Presenter presenter) {

         presenter.start();
    }

    @Override
    public void setLongComments(List<Comment> longComments) {

         mCommentList.add(longComments);
    }

    @Override
    public void setShortComments(List<Comment> shortComments) {

        mCommentList.add(shortComments);
        mExpandableListView.setAdapter(mCommentAdapter);
    }
}
