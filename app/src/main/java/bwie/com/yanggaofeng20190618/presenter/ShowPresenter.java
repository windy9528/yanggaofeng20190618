package bwie.com.yanggaofeng20190618.presenter;

import android.widget.BaseExpandableListAdapter;

import bwie.com.yanggaofeng20190618.core.BasePresenter;
import bwie.com.yanggaofeng20190618.core.DataCall;
import bwie.com.yanggaofeng20190618.model.DataModel;
import io.reactivex.Observable;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class ShowPresenter extends BasePresenter {

    public ShowPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(DataModel dataModel, Object... args) {
        return dataModel.show(
                String.valueOf(args[0]),
                String.valueOf(args[1])
        );
    }
}
