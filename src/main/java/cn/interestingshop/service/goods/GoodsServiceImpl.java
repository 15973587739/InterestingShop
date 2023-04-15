package cn.interestingshop.service.goods;
import java.sql.Connection;
import java.util.List;

import cn.interestingshop.dao.goods.GoodsMapper;
import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.interestingshop.entity.Goods;
import cn.interestingshop.utils.DataSourceUtil;

/**
 * 商品的业务类
 */
public class GoodsServiceImpl implements GoodsService {
	
	private Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	
	@Override
	public boolean save(Goods goods) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(GoodsMapper.class).save(goods)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public boolean update(Goods goods) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(GoodsMapper.class).update(goods)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public boolean deleteById(Integer goodsId) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(GoodsMapper.class).deleteById(goodsId)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public Goods getById(Integer goodsId) {
		Goods goods=null;
		SqlSession sqlSession=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			goods = sqlSession.getMapper(GoodsMapper.class).selectById(goodsId);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return goods;
	}

	@Override
	public List<Goods> getList(Integer currentPageNo,Integer pageSize,String goodsName, Integer categoryId) {
		SqlSession sqlSession=null;
		List<Goods> productList=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			productList = sqlSession.getMapper(GoodsMapper.class).selectList(currentPageNo, pageSize,goodsName,categoryId);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return productList;
	}

	@Override
	public int getCount(String goodsName,Integer categoryId) {
		Integer count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession= MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(GoodsMapper.class).selectCount(goodsName,categoryId);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}

	@Override
	public boolean updateStock(Integer goodsId, Integer stock) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(GoodsMapper.class).updateStock(goodsId,stock)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}
   
}
