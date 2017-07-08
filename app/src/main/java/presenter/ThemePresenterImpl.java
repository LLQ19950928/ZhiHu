package presenter;

import android.util.Log;

import contract.ThemeContract;
import model.Theme;
import model.ThemeModel;
import util.Constant;
import util.HttpHandler;

/**
 * Created by Administrator on 2016/9/28.
 */

public class ThemePresenterImpl implements Presenter {

    private ThemeModel themeModel;
    private ThemeContract.ThemeView themeView;
    public static final String TAG = "tHEMEEE";
    public ThemePresenterImpl(ThemeModel themeModel, 
                              ThemeContract.ThemeView themeView) {
        this.themeModel = themeModel;
        this.themeView = themeView;
        themeView.setPresenter(this);
    }

    @Override
    public void start() {

        handleInfo();
    }


    private void handleInfo() {
        
        themeModel.getInfoFromHttp(Constant.THEME, new HttpHandler() {
            @Override
            public void onFinish(Object o) {
                
                if(o instanceof Theme) {
                    Log.d(TAG, "onFinish: "+ "hehehehhe");
                    themeView.initList(((Theme) o).getOthers());
                }
            }
        });
    }
    
    
}
