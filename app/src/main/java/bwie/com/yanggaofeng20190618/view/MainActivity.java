package bwie.com.yanggaofeng20190618.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.yanggaofeng20190618.R;
import bwie.com.yanggaofeng20190618.adapter.MyAdapter;
import bwie.com.yanggaofeng20190618.core.DataCall;
import bwie.com.yanggaofeng20190618.entity.Goods;
import bwie.com.yanggaofeng20190618.entity.Result;
import bwie.com.yanggaofeng20190618.entity.Shop;
import bwie.com.yanggaofeng20190618.presenter.ShowPresenter;

public class MainActivity extends AppCompatActivity implements DataCall<List<Shop>> {

    private RecyclerView recyclerView;
    private TextView totalPrice;
    private CheckBox checkBoxAll;
    private Button btnGo;
    private MyAdapter myAdapter;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        totalPrice = findViewById(R.id.total_price);
        checkBoxAll = findViewById(R.id.checkBox_all);
        btnGo = findViewById(R.id.btn_go);
        showPresenter = new ShowPresenter(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        showPresenter.getData("1229", "15608682750591229");

        myAdapter.setShowPrice(new MyAdapter.ShowPrice() {
            @Override
            public void showItem(double zongjia, int shulaing, boolean zhuangtai) {

                totalPrice.setText("总价:￥" + zongjia);
                btnGo.setText("去结算(" + shulaing + ")");
                checkBoxAll.setChecked(zhuangtai);
            }
        });

        //全选功能
        checkBoxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkBoxAll.isChecked();
                myAdapter.checkAll(checked);
            }
        });

        //去结算
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
    }

    @Override
    public void success(Result data) {

        List<Shop> shopList = (List<Shop>) data.getResult();
        List<Goods> list = new ArrayList<>();
        for (int i = 0; i < shopList.size(); i++) {
            Shop shop = shopList.get(i);
            List<Goods> shoppingCartList = shop.getShoppingCartList();
            list.addAll(shoppingCartList);
        }
        myAdapter.addList(list);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
