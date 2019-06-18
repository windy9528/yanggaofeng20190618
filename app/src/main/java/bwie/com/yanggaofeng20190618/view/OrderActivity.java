package bwie.com.yanggaofeng20190618.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.yanggaofeng20190618.R;
import bwie.com.yanggaofeng20190618.adapter.MyAdapter;
import bwie.com.yanggaofeng20190618.core.DataCall;
import bwie.com.yanggaofeng20190618.entity.Goods;
import bwie.com.yanggaofeng20190618.entity.Result;
import bwie.com.yanggaofeng20190618.entity.Shop;
import bwie.com.yanggaofeng20190618.presenter.ShowPresenter;
import bwie.com.yanggaofeng20190618.presenter.UpIconPresenter;

public class OrderActivity extends AlbumActivity implements DataCall {

    private SimpleDraweeView imageIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        imageIcon = findViewById(R.id.image_icon);

        imageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, AlbumActivity.PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null, requestCode, data);
            if (!TextUtils.isEmpty(filePath)) {
                UpIconPresenter upIconPresenter = new UpIconPresenter(this);

                upIconPresenter.getData("1229", "15608682750591229",
                        filePath);
            }
        }
    }


    @Override
    public void success(Result data) {
        String headPath = data.getHeadPath();

        imageIcon.setImageURI(Uri.parse(headPath));
    }

    @Override
    public void fail(Result result) {
        Toast.makeText(this, "上传错误", Toast.LENGTH_SHORT).show();
    }
}
