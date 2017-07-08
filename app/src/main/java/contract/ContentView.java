package contract;


import model.ExtraStory;
import view.BaseView;

/**
 * Created by lenovo on 2016/10/10.
 */

public interface ContentView extends BaseView {


    void setWebViewContent(String body);
    void setCountOfComments(String countOfComments);
    int getNewsId();
    void setCountOfThumbs(String countOfThumbs);
    void setExtraStory(ExtraStory extraStory);
    void setShareUrl(String shareUrl);
}
