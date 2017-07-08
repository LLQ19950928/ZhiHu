package contract;

import java.util.List;

import model.Other;
import presenter.Presenter;
import view.BaseView;

/**
 * Created by Administrator on 2016/9/28.
 */

public interface ThemeContract {

    interface ThemeView extends BaseView {

        void initList(List<Other> others);
    }


}
