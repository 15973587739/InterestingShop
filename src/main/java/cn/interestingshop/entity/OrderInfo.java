package cn.interestingshop.entity;

import java.io.Serializable;

public class OrderInfo implements Serializable{
	private Integer id;		//ID
	private Integer baseOrderId;//订单ID
	private Integer buyNum;	//数量
	private Float amount;		//单价
	private Integer goodsId;	//商品id

	private Goods goods;//商品

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Integer baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}


	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
}
