package util;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2016/9/30.
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        GlideUtil.setImageBackground(context, (String) path, imageView);
    }
}
