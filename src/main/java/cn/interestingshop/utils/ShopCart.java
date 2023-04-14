package cn.interestingshop.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.entity.Goods;

/**
 * 购物车
 * 
 * @author InterestingShop.team
 */
public class ShopCart implements Serializable {
	public List<ShopGoods> shopGoodsList = new ArrayList<ShopGoods>();
	private Double sum;

	// 获取购物车中所有商品
	public List<ShopGoods> getShopGoodsList() {
		return shopGoodsList;
	}

	// 添加一项
	public ReturnResult addShopGoods(Goods goods, Integer buyNum) {
		ReturnResult result = new ReturnResult();
		int flag = 0;
		for (int i = 0; i < shopGoodsList.size(); i++) {
			// 判断购物车中是否已有此商品，如果有则累计数量
			if ((shopGoodsList.get(i).getGoods().getId()).equals(goods.getId())) {
				if (shopGoodsList.get(i).getBuyNum() + buyNum > goods.getStock()) {
					return result.returnFail("商品数量不足");
				} else {
					shopGoodsList.get(i).setBuyNum(shopGoodsList.get(i).getBuyNum() + buyNum);
					flag = 1;
				}
			}
		}
		if (buyNum > goods.getStock()) {
			return result.returnFail("商品数量不足");
		}
		if (flag == 0) {
			shopGoodsList.add(new ShopGoods(goods, buyNum));
		}
		return result.returnSuccess();
	}

	// 移除一项
	public void removeShopGoods(int index) {
		shopGoodsList.remove(index);
	}

	// 修改数量
	public void updateBuyNum(int index, Integer buyNum) {
		shopGoodsList.get(index).setBuyNum(buyNum);
	}

	// 计算总价格
	public float getAmount() {
		float sum = 0;
		for (ShopGoods shopGoods : shopGoodsList) {
			sum = sum + shopGoods.getAmount();
		}
		return sum;
	}

	public void setShopGoodsList(List<ShopGoods> shopGoodsList) {
		this.shopGoodsList = shopGoodsList;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}
}
