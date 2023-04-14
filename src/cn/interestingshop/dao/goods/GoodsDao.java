package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.Goods;

/**
 * 商品查询Dao
 *
 * deleteById(Integer id)
 * getById(Integer id)
 * getRowCount(params)
 * getRowList(params)
 *
 */
public interface GoodsDao extends IBaseDao {

	Integer updateStock(Integer id, Integer buyNum) throws Exception;
	
	public Integer save(Goods goods) throws Exception;

	public Integer update(Goods goods) throws Exception;
	
	public Integer deleteById(Integer id) throws Exception;
	
	public Goods selectById(Integer id)throws Exception;
	
	public List<Goods> selectList(Integer currentPageNo,Integer pageSize,String goodsName,Integer categoryId)throws Exception;
	
	public Integer selectCount(String goodsName,Integer categoryId)throws Exception;
}
