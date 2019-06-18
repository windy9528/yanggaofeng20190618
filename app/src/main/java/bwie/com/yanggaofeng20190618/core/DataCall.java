package bwie.com.yanggaofeng20190618.core;

import bwie.com.yanggaofeng20190618.entity.Result;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public interface DataCall<T> {

    void success(Result data);

    void fail(Result result);

}
