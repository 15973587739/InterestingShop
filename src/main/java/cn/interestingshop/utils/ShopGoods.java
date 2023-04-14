package cn.interestingshop.utils;

import cn.interestingshop.entity.Goods;

import java.io.Serializable;

/**
 * 购物车商品
 * @author InterestingShop.team
 */
public class ShopGoods implements Serializable{
	
	private Goods goods;	//商品
	private Integer buyNum;	//数量
	private float amount;	//价格

	public ShopGoods(Goods goods, Integer buyNum) {
		this.goods = goods;
		this.buyNum = buyNum;
		this.amount = goods.getPrice() * buyNum;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
		this.amount = goods.getPrice() * buyNum;
	}

	public Goods getGoods() {
		return goods;
	}

	public float getAmount() {
		return amount;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}
