package bwie.com.yanggaofeng20190618.core;

import android.view.animation.ScaleAnimation;

import bwie.com.yanggaofeng20190618.entity.Result;
import bwie.com.yanggaofeng20190618.model.DataModel;
import bwie.com.yanggaofeng20190618.util.RetrofitUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public abstract class BasePresenter {

    private DataCall dataCall;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public void getData(Object... args) {

        final DataModel dataModel = RetrofitUtil.getInstance().create(DataModel.class);

        getModel(dataModel, args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, Result>() {
                    @Override
                    public Result apply(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        return new Result();
                    }
                })
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        if ("0000".equals(result.getStatus())) {
                            dataCall.success(result);
                        } else {
                            dataCall.fail(result);
                        }
                    }
                });
    }

    protected abstract Observable getModel(DataModel dataModel, Object... args);
}
