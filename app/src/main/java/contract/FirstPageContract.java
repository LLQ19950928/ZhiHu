package contract;

import java.util.List;

import model.Story;
import model.TopStory;
import presenter.Presenter;
import view.BaseView;

/**
 * Created by Administrator on 2016/9/29.
 */

public interface FirstPageContract {

    interface FirstPageView extends BaseView {

        void setTopStories(List<TopStory> topStories);
        void setStories(List<Story> stories);
        void setDate(String date);
    }


}
