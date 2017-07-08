package util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Administrator on 2016/9/30.
 */

public class GlideUtil {

     public static void setImageBackground(Context context, String url,
                                           ImageView imageView) {

         if(context != null) {
             Glide.with(context)
                     .load(url)
                     .into(imageView);
         }

     }

     public static void setCircleImage(final Context context, String url,
                                       final ImageView imageView) {

         Glide.with(context).load(url).asBitmap().centerCrop().into(
                 new BitmapImageViewTarget(imageView) {
             @Override
             protected void setResource(Bitmap resource) {
                 RoundedBitmapDrawable circularBitmapDrawable =
                         RoundedBitmapDrawableFactory.create(context.getResources(),
                                 resource);
                 circularBitmapDrawable.setCircular(true);
                 imageView.setImageDrawable(circularBitmapDrawable);
             }
         });
     }
}
