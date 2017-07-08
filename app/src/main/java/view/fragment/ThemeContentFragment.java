package view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.llq.zhihu.R;

import java.util.ArrayList;
import java.util.List;

import adapter.CircleImageAdapter;
import adapter.FirstPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.ThemeContentContract;
import model.Editor;
import model.Story;
import presenter.Presenter;
import presenter.ThemeContentPresenterImpl;
import util.FragmentUtil;
import util.GlideUtil;
import view.activity.EditorActivity;
import view.activity.NewsContentActivity;
import view.activity.ThemeContentActivity;

/**
 * Created by Administrator on 2016/9/30.
 */

public class ThemeContentFragment extends Fragment
    implements ThemeContentContract.ThemeContentView,
        FirstPageAdapter.OnClickedListener {

    @BindView(R.id.first_page_recycler_view)
    RecyclerView recyclerView;
    private FirstPageAdapter themeContentAdapter;
    private ImageView backgroundImageView;
    private TextView introduceTextView;
    private RecyclerView editorRecyclerView;
    private CircleImageAdapter circleImageAdapter;
    private LinearLayout editorLinearLayout;
    private ArrayList<Editor> editorList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_first_page, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        themeContentAdapter = new FirstPageAdapter(getActivity());
        themeContentAdapter.setOnClickListener(this);
         View view = LayoutInflater.from(getActivity()).inflate(
                 R.layout.theme_head, recyclerView, false);
         editorLinearLayout = (LinearLayout)view.findViewById(R.id.editor_linear_layout);
         editorLinearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), EditorActivity.class);
                 intent.putExtra("editorList", editorList);
                 startActivity(intent);
             }
         });
         backgroundImageView = (ImageView)view.findViewById(R.id.image_view);
         introduceTextView = (TextView)view.findViewById(R.id.text_view);
         editorRecyclerView = (RecyclerView)view.findViewById(R.id.editor_recycler_view);
         editorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                 LinearLayoutManager.HORIZONTAL, false));
         themeContentAdapter.setView(view);
         new ThemeContentPresenterImpl(this);

    }


    @Override
    public void setPresenter(Presenter presenter) {

         presenter.start();
    }

    @Override
    public void setBackground(String imageUrl) {

        GlideUtil.setImageBackground(getActivity(),
                imageUrl, backgroundImageView);
    }

    @Override
    public void setIntroduce(String introduce) {

        introduceTextView.setText(introduce);
    }

    @Override
    public void setStories(List<Story> stories) {

        themeContentAdapter.addData(stories);
        recyclerView.setAdapter(themeContentAdapter);
    }

    @Override
    public void setEditors(List<Editor> editors) {

        this.editorList = (ArrayList<Editor>) editors;
        circleImageAdapter = new CircleImageAdapter(getActivity(), editors);
        editorRecyclerView.setAdapter(circleImageAdapter);
    }

    @Override
    public int getThemeId() {

        return getArguments().getInt("theme_id");
    }

    public void returnTop() {

        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onClicked(int id) {

        Intent intent = new Intent(getActivity(), ThemeContentActivity.class);
        intent.putExtra("newsID", id);
        startActivity(intent);
    }
}
