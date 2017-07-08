package view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llq.zhihu.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import adapter.FirstPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.FirstPageContract;
import model.Story;
import model.TopStory;
import presenter.FirstPagePresenterImpl;
import presenter.Presenter;
import util.FragmentUtil;
import util.GlideImageLoader;
import view.activity.NewsContentActivity;

/**
 * Created by Administrator on 2016/9/29.
 */

public class FirstPageFragment extends Fragment
        implements FirstPageContract.FirstPageView,
        FirstPageAdapter.OnClickedListener {

    @BindView(R.id.first_page_recycler_view)
    RecyclerView recyclerView;
    private FirstPageAdapter firstPageAdapter;
    private Banner banner;
    private LinearLayoutManager linearLayoutManager;
    private FirstPagePresenterImpl firstPagePresenter;
    private String date;
    public static final String TAG = "新闻日期是：";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_first_page,
                container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.ibanner, recyclerView, false);
        banner = (Banner)view.findViewById(R.id.banner);

        //设置Banner属性
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setIndicatorGravity(BannerConfig.CENTER);

        firstPageAdapter = new FirstPageAdapter(getActivity());
        firstPageAdapter.setOnClickListener(this);
        recyclerView.setAdapter(firstPageAdapter);
        firstPageAdapter.setView(view);
        new FirstPagePresenterImpl(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(!firstPagePresenter.isLoad() &&
                        linearLayoutManager.findLastVisibleItemPosition()
                        <= linearLayoutManager.getItemCount() - 2) {
                      firstPagePresenter.loadMore(date);
                }
            }
        });

    }

    public void returnTop() {

        recyclerView.scrollToPosition(0);
    }

    @Override
    public void setTopStories(final List<TopStory> topStories) {

        List<String> imgUrls = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (TopStory top_story : topStories) {

            if(top_story.getImage() != null)
               imgUrls.add(top_story.getImage());
            titles.add(top_story.getTitle());
        }
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgUrls);
        banner.setBannerTitles(titles);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {

                Intent intent = new Intent(getActivity(), NewsContentActivity.class);
                intent.putExtra("newsID", topStories.get(position - 1).getId());
                startActivity(intent);
            }
        });
        banner.start();
    }

    @Override
    public void setStories(List<Story> stories) {

          firstPageAdapter.addData(stories);
    }

    @Override
    public void setPresenter(Presenter presenter) {

        presenter.start();
        firstPagePresenter = (FirstPagePresenterImpl)presenter;
    }

    @Override
    public void setDate(String date) {

        this.date = date;
        Log.d(TAG, "setDate: " + date);
    }

    @Override
    public void onClicked(int id) {

        Intent intent = new Intent(getActivity(), NewsContentActivity.class);
        intent.putExtra("newsID", id);
        startActivity(intent);
    }
}
