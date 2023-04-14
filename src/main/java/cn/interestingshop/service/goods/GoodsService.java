package cn.interestingshop.service.goods;

import java.util.List;

import cn.interestingshop.entity.Goods;

public interface GoodsService {
	
	public boolean save(Goods goods);
	
	public boolean update(Goods goods);
	
	public boolean deleteById(Integer goodsId);
	
	public Goods getById(Integer goodsId);
	
	public List<Goods> getList(Integer currentPageNo,Integer pageSize,
										String goodsName,Integer categoryId);
	
	public int getCount(String goodsName,Integer categoryId);
	
	public boolean updateStock(Integer goodsId,Integer stock);
	
}
