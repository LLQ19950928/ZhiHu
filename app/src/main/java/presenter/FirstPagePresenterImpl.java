package presenter;

import android.util.Log;

import java.util.List;

import contract.FirstPageContract;
import model.Before;
import model.Latest;
import model.Story;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by Administrator on 2016/9/30.
 */

public class FirstPagePresenterImpl
        implements Presenter {

    private FirstPageContract.FirstPageView firstPageView;
    private boolean isLoad;
    private List<Story> stories;

    public static final String TAG = "新闻的日期是";

    public FirstPagePresenterImpl(FirstPageContract.FirstPageView
                                          firstPageView) {
        this.firstPageView = firstPageView;
        firstPageView.setPresenter(this);
    }

    private void handleInfo() {

        //获取新闻
        HttpUtil.getInfoFromHttp(Constant.LATEST, new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if (o instanceof Latest) {

                    firstPageView.setTopStories(((Latest) o).getTop_stories());
                    stories = ((Latest) o).getStories();
                    //给第一个Story设置日期
                    stories.get(0).setDate(((Latest) o).getDate());
                    firstPageView.setStories(stories);
                    firstPageView.setDate(((Latest) o).getDate());
                }
            }
        }, Latest.class);
    }

    @Override
    public void start() {

        handleInfo();
    }

    public void loadMore(String date) {

        setLoad(true);
        HttpUtil.getInfoFromHttp(Constant.BEFORE + date, new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if (o instanceof Before) {

                    Log.d(TAG, "onFinish: " + ((Before) o).getDate());
                    stories = ((Before) o).getStories();
                    //给第一个Story设置日期
                    stories.get(0).setDate(((Before) o).getDate());
                    firstPageView.setDate(((Before) o).getDate());
                    firstPageView.setStories(stories);
                    setLoad(false);
                }

            }
        }, Before.class);

    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }


}
