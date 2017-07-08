package view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.llq.zhihu.R;

import java.util.ArrayList;
import java.util.List;

import adapter.ThemeAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.ThemeContract;
import model.Other;
import model.ThemeModel;
import presenter.Presenter;
import presenter.ThemePresenterImpl;
import view.activity.MainActivity;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ThemeFragment extends Fragment
        implements ThemeContract.ThemeView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.first_page_linearLayout)
    LinearLayout linearLayout;
    private ThemeAdapter themeAdapter;
    private List<Other> others = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    @Override
    public void setPresenter(Presenter presenter) {

        presenter.start();
    }

    private void initView() {

        themeAdapter = new ThemeAdapter(others, getActivity());
        new ThemePresenterImpl(new ThemeModel(), this);

    }

    @Override
    public void initList(List<Other> others) {

        for (Other other: others){
            this.others.add(other);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(themeAdapter);
    }


    @OnClick(R.id.first_page_linearLayout)
    public void onFirstPageClicked() {

        ((MainActivity)getActivity()).returnFirstPage();
    }
}
