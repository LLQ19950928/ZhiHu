package presenter;


import contract.NewsContentView;
import model.ExtraStory;
import model.NewsContent;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by lenovo on 2016/10/10.
 */

public class NewsContentPresenterImpl implements Presenter {

    private NewsContentView newsContentView;

    public NewsContentPresenterImpl(NewsContentView newsContentView) {

        this.newsContentView = newsContentView;
        newsContentView.setPresenter(this);
    }

    @Override
    public void start() {

         handlerInfo();
         handlerExtraOfStory();
    }

    private void handlerInfo() {

        HttpUtil.getInfoFromHttp(Constant.NEWS_CONTENT
                + newsContentView.getNewsId(), new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof NewsContent) {

                    newsContentView.setImageBackground(((NewsContent) o).getImage());
                    newsContentView.setTitleText(((NewsContent) o).getTitle());
                    newsContentView.setWebViewContent(((NewsContent) o).getBody());
                    newsContentView.setShareUrl(((NewsContent) o).getShare_url());
                }
            }
        }, NewsContent.class);
    }

    private void handlerExtraOfStory() {

        HttpUtil.getInfoFromHttp(Constant.EXTRA_STORY +
                newsContentView.getNewsId(), new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof ExtraStory) {
                    newsContentView.setCountOfComments(
                            String.valueOf(((ExtraStory) o).getComments()));
                    newsContentView.setCountOfThumbs(
                            String.valueOf(((ExtraStory) o).getPopularity())
                    );
                    newsContentView.setExtraStory((ExtraStory) o);
                }
            }
        }, ExtraStory.class);
    }
}
