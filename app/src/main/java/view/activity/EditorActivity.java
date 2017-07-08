package view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.llq.zhihu.R;

import java.util.List;

import adapter.EditorAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Editor;

/**
 * Created by lenovo on 2016/10/14.
 */

public class EditorActivity extends AppCompatActivity {

    @BindView(R.id.editor_toolBar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView editorRecyclerView;
    private EditorAdapter editorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_editor);
        ButterKnife.bind(this);
        initView();
        super.onCreate(savedInstanceState);
    }

    private void initView() {

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editorAdapter = new EditorAdapter(this,
                (List<Editor>) getIntent().getSerializableExtra("editorList"));
        editorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        editorRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        editorRecyclerView.setAdapter(editorAdapter);
    }
}
