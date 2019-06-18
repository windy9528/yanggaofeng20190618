package bwie.com.yanggaofeng20190618.presenter;

import java.io.File;

import bwie.com.yanggaofeng20190618.core.BasePresenter;
import bwie.com.yanggaofeng20190618.core.DataCall;
import bwie.com.yanggaofeng20190618.model.DataModel;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class UpIconPresenter extends BasePresenter {

    public UpIconPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(DataModel dataModel, Object... args) {

        MultipartBody.Builder builder = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM);
        File file = new File((String) args[2]);
        builder.addFormDataPart("image", file.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"),
                        file));
        return dataModel.upIncon(
                String.valueOf(args[0]),
                (String) args[1], builder.build());
    }
}
