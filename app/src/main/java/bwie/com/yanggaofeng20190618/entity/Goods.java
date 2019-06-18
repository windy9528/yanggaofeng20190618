package bwie.com.yanggaofeng20190618.entity;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class Goods {

    private int commodityId;
    private String commodityName;
    private int count = 1;
    private String pic;
    private double price;
    public boolean goodCheck;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
