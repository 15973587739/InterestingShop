package cn.interestingshop.service.goods;
import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import cn.interestingshop.dao.goods.GoodsDao;
import cn.interestingshop.dao.goods.GoodsDaoImpl;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.utils.DataSourceUtil;

/**
 * 商品的业务类
 */
public class GoodsServiceImpl implements GoodsService {
	
	private Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	
	@Override
	public boolean save(Goods goods) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			count=goodsDao.save(goods);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count>0;
	}

	@Override
	public boolean update(Goods goods) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			count=goodsDao.update(goods);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count>0;
	}

	@Override
	public boolean deleteById(Integer goodsId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			count=goodsDao.deleteById(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count>0;
	}

	@Override
	public Goods getById(Integer goodsId) {
		Connection connection = null;
		Goods goods=null;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			goods=goodsDao.selectById(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return goods;
	}

	@Override
	public List<Goods> getList(Integer currentPageNo,Integer pageSize,String goodsName, Integer categoryId) {
		Connection connection = null;
		List<Goods> productList=null;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			productList=goodsDao.selectList(currentPageNo,pageSize,goodsName,categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return productList;
	}

	@Override
	public int getCount(String goodsName,Integer categoryId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			count=goodsDao.selectCount(goodsName,categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}

	@Override
	public boolean updateStock(Integer goodsId, Integer stock) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			GoodsDao goodsDao = new GoodsDaoImpl(connection);
			count=goodsDao.updateStock(goodsId,stock);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count>0;
	}
   
}
