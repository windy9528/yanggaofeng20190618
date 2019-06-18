package bwie.com.yanggaofeng20190618.model;

import java.util.List;

import bwie.com.yanggaofeng20190618.entity.Result;
import bwie.com.yanggaofeng20190618.entity.Shop;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public interface DataModel {

    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<Shop>>> show(@Header("userId") String uid,
                                        @Header("sessionId") String sid);


    //上传头像
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result> upIncon(@Header("userId") String uid,
                               @Header("sessionId") String sessionId,
                               @Body MultipartBody body);
}
