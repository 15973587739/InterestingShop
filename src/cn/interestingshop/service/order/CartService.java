package cn.interestingshop.service.order;

import cn.interestingshop.utils.ShopCart;

/**
 * Created by bdqn on 2016/5/11.
 */
public interface CartService {

    public ShopCart update(String goodsId,String buyNumStr,ShopCart cart) throws Exception;

    public ShopCart calculate(ShopCart cart)throws Exception;
}
