package cn.interestingshop.service.order;

import java.sql.Connection;

import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.ShopCart;
import cn.interestingshop.utils.ShopGoods;

/**
 * Created by bdqn on 2016/5/11.
 */
public class CartServiceImpl implements CartService {
	
    @Override
    public ShopCart update(String goodsId, String buyNumStr, ShopCart cart) throws Exception {
    	Integer buyNum = 0;
    	Connection connection = null;
    	if (!EmptyUtils.isEmpty(buyNumStr)) {
            buyNum = Integer.parseInt(buyNumStr);
        }
        //便利购物车寻找该商品 修改其数量
        for (ShopGoods shopGoods : cart.getShopGoodsList()) {
            if (shopGoods.getGoods().getId().toString().equals(goodsId)) {
                if (buyNum == 0 || buyNum < 0) {
                    cart.getShopGoodsList().remove(shopGoods);
                    break;
                } else {
                	shopGoods.setBuyNum(buyNum);
                }
            }
        }
        //重新计算金额
        calculate(cart);
        return cart;
    }

    /**
     * 核算购物车的金额
     *
     * @param cart
     * @return
     * @throws Exception
     */
    @Override
    public ShopCart calculate(ShopCart cart) throws Exception {
        double sum = 0.0;
        for (ShopGoods shopGoods : cart.getShopGoodsList()) {
            sum = sum + shopGoods.getBuyNum() * shopGoods.getGoods().getPrice();
            shopGoods.setAmount(shopGoods.getBuyNum() * shopGoods.getGoods().getPrice());
        }
        cart.setSum(sum);
        return cart;
    }
}
