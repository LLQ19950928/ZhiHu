package presenter;


import contract.ThemeContentContract;
import model.ThemeContent;
import util.Constant;
import util.HttpHandler;
import util.HttpUtil;

/**
 * Created by lenovo on 2016/10/9.
 */

public class ThemeContentPresenterImpl implements Presenter {

    private ThemeContentContract.ThemeContentView themeContentView;

    public ThemeContentPresenterImpl(ThemeContentContract.ThemeContentView
                                             themeContentView) {
        this.themeContentView = themeContentView;
        themeContentView.setPresenter(this);
    }

    @Override
    public void start() {

        handleInfo();
    }

    private void handleInfo() {

        HttpUtil.getInfoFromHttp(Constant.CONTENT_THEME
                + themeContentView.getThemeId(),
                new HttpHandler() {
            @Override
            public void onFinish(Object o) {

                if(o instanceof ThemeContent) {

                    themeContentView.setBackground(((ThemeContent) o).getBackground());
                    themeContentView.setIntroduce(((ThemeContent) o).getDescription());
                    themeContentView.setStories(((ThemeContent) o).getStories());
                    themeContentView.setEditors(((ThemeContent) o).getEditors());
                }
            }
        }, ThemeContent.class);
    }


}
