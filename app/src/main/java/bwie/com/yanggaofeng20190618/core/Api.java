package bwie.com.yanggaofeng20190618.core;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class Api extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);  //初始化fresco
    }
}
