package bwie.com.yanggaofeng20190618.util;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class RetrofitUtil {

    private static RetrofitUtil instance;
    private Retrofit retrofit;

    private RetrofitUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                //.baseUrl("http://172.17.8.100/small/")
                .baseUrl("http://mobile.bwstudent.com/small/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public static RetrofitUtil getInstance() {
        if (instance == null)
            instance = new RetrofitUtil();
        return instance;
    }
}
