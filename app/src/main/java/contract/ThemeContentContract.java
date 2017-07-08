package contract;

import java.util.List;

import model.Editor;
import model.Story;
import view.BaseView;

/**
 * Created by Administrator on 2016/9/30.
 */

public interface ThemeContentContract {

    interface ThemeContentView extends BaseView {

        void setBackground(String imageUrl);
        void setIntroduce(String introduce);
        void setStories(List<Story> stories);
        void setEditors(List<Editor> editors);
        int getThemeId();
    }
}
