package cn.interestingshop.entity;

import java.io.Serializable;

public class Goods implements Serializable{
	private Integer id;				//ID
	private String goodsName;		//商品名
	private String goodsDesc;		//描述
	private Float price;			//单价
	private Integer stock;			//数量
	private Integer classifyLevel1Id;//一级分类
	private Integer classifyLevel2Id;//二级分类
	private Integer classifyLevel3Id;//三级分类
	private String fileName;			//图片名称
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goods other = (Goods) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getClassifyLevel1Id() {
		return classifyLevel1Id;
	}

	public void setClassifyLevel1Id(Integer classifyLevel1Id) {
		this.classifyLevel1Id = classifyLevel1Id;
	}

	public Integer getClassifyLevel2Id() {
		return classifyLevel2Id;
	}

	public void setClassifyLevel2Id(Integer classifyLevel2Id) {
		this.classifyLevel2Id = classifyLevel2Id;
	}

	public Integer getClassifyLevel3Id() {
		return classifyLevel3Id;
	}

	public void setClassifyLevel3Id(Integer classifyLevel3Id) {
		this.classifyLevel3Id = classifyLevel3Id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}