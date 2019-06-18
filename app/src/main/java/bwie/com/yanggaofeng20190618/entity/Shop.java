package bwie.com.yanggaofeng20190618.entity;

import java.util.List;

/**
 * date:2019/6/18
 * name:windy
 * function:
 */
public class Shop {

    private String categoryName;
    private List<Goods> shoppingCartList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Goods> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<Goods> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
    }
}
