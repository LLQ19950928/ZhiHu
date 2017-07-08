package util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.llq.zhihu.R;

/**
 * Created by lenovo on 2016/10/15.
 */

public class FragmentUtil {

    public static void transaction(FragmentActivity activity, Fragment fragment) {

        activity.getSupportFragmentManager().beginTransaction().replace(
                R.id.frame_layout, fragment).commit();
    }
}
