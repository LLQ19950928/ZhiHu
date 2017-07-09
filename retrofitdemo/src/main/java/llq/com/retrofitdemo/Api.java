package llq.com.retrofitdemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 刘柳青 on 2017/4/26.
 */

public interface Api {

    @GET("themes")
    Call<ResponseBody> getZhiHuTheme();

    @GET("theme/{id}")
    Call<ResponseBody> getZhiHuAnyTheme(@Path("id") int id);

    @GET("themes")
    Call<Other> getZhiHuWithGson();
}
