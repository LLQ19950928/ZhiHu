package presenter;

import contract.ContentView;
import model.ExtraStory;
import model.NewsContent;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by lenovo on 2016/10/10.
 */

public class ThemeNewsPresenterImpl implements Presenter {

    private ContentView contentView;

    public ThemeNewsPresenterImpl(ContentView contentView) {

        this.contentView = contentView;
        contentView.setPresenter(this);
    }

    @Override
    public void start() {

        handlerInfo();
        handlerExtraOfStory();
    }

    private void handlerInfo() {

        HttpUtil.getInfoFromHttp(Constant.NEWS_CONTENT
                + contentView.getNewsId(), new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof NewsContent) {

                    contentView.setWebViewContent(((NewsContent) o).getBody());
                    contentView.setShareUrl(((NewsContent) o).getShare_url());
                }
            }
        }, NewsContent.class);
    }

    private void handlerExtraOfStory() {

        HttpUtil.getInfoFromHttp(Constant.EXTRA_STORY +
                contentView.getNewsId(), new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof ExtraStory) {
                    contentView.setCountOfComments(
                            String.valueOf(((ExtraStory) o).getComments()));
                    contentView.setCountOfThumbs(
                            String.valueOf(((ExtraStory) o).getPopularity())
                    );
                    contentView.setExtraStory((ExtraStory) o);
                }
            }
        }, ExtraStory.class);
    }
}
