package bwie.com.yanggaofeng20190618.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bwie.com.yanggaofeng20190618.R;
import bwie.com.yanggaofeng20190618.entity.Goods;
import bwie.com.yanggaofeng20190618.entity.Shop;
import bwie.com.yanggaofeng20190618.view.MainActivity;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Goods> list;

    public MyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Goods goods = list.get(i);

        Glide.with(context)
                .load(goods.getPic())
                .into(viewHolder.ivIcon);
        viewHolder.tvPrice.setText("合计:￥" + goods.getPrice());
        viewHolder.tvNum.setText(String.valueOf(goods.getCount()));

        viewHolder.checkbox.setChecked(goods.goodCheck);
        viewHolder.checkbox.setTag(goods);

        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goods g = (Goods) v.getTag();
                g.goodCheck = ((CheckBox) v).isChecked();
                calculate();
                notifyDataSetChanged();
            }
        });

        //加减器
        viewHolder.add.setTag(goods);
        viewHolder.minus.setTag(goods);

        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加
                Goods g = (Goods) v.getTag();

                int count = g.getCount();
                count++;
                g.setCount(count);
                calculate();
                notifyDataSetChanged();
            }
        });

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加
                Goods g = (Goods) v.getTag();
                int count = g.getCount();
                if (count > 1) {
                    count--;
                } else {
                    Toast.makeText(context, "不能减", Toast.LENGTH_SHORT).show();
                }
                g.setCount(count);
                calculate();
                notifyDataSetChanged();
            }
        });
    }

    public void calculate() {
        double price = 0;  //价格
        int num = 0; //数量
        boolean status = true; //状态
        for (int i = 0; i < list.size(); i++) {
            Goods goods = list.get(i);
            if (goods.goodCheck) {//如果选中 则计算价格
                price += goods.getPrice() * goods.getCount();
                num += goods.getCount();
            }
            if (!goods.goodCheck) //如果有一个不选中 则改变状态
                status = false;
        }

        showPrice.showItem(price, num, status);
    }

    public ShowPrice showPrice;

    public void setShowPrice(ShowPrice showPrice) {
        this.showPrice = showPrice;
    }

    /**
     * 全选
     *
     * @param checked
     */
    public void checkAll(boolean checked) {
        for (int i = 0; i < list.size(); i++) {
            Goods goods = list.get(i);
            goods.goodCheck = checked;
        }
        notifyDataSetChanged();
    }

    public interface ShowPrice {

        //价格、数量、复选框状态
        void showItem(double zongjia, int shulaing, boolean zhuangtai);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<Goods> data) {
        if (data != null)
            list.addAll(data);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkbox;
        ImageView ivIcon;
        TextView tvName;
        TextView tvPrice;
        TextView minus;
        TextView tvNum;
        TextView add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            minus = (TextView) itemView.findViewById(R.id.minus);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
            add = (TextView) itemView.findViewById(R.id.add);
        }
    }
}
