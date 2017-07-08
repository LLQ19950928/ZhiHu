package contract;


import presenter.Presenter;
import view.BaseView;

/**
 * Created by Administrator on 2016/9/28.
 */

public interface TaskContract {

    interface StartView extends BaseView {

        void setBackground(String url);
    }


}
