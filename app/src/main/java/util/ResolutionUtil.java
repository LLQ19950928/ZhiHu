package util;


import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;


/**
 * Created by 刘柳青 on 2017/1/26.
 */

public class ResolutionUtil {

    public static float[] getResolution(AppCompatActivity activity) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new float[]{displayMetrics.widthPixels,
                displayMetrics.heightPixels};
    }


}
