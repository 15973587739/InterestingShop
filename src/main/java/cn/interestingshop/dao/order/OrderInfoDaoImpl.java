package cn.interestingshop.dao.order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.dao.goods.GoodsDao;
import cn.interestingshop.dao.goods.GoodsDaoImpl;
import cn.interestingshop.entity.Goods;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.param.OrderInfoParam;
import cn.interestingshop.utils.EmptyUtils;

/**
 * Created by bdqn on 2016/5/8.
 */
public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao{

    GoodsDao goodsDao;

    public OrderInfoDaoImpl(Connection connection) {
        super(connection);
        goodsDao=new GoodsDaoImpl(connection);
    }
    

    public void save(OrderInfo detail) throws SQLException {//保存订单详情
        Integer id=0;
		String sql=" insert into t_order_info(baseOrderId,goodsId,buyNum,amount) values(?,?,?,?) ";
        try {
            Object param[]=new Object[]{detail.getBaseOrderId(),detail.getGoodsId(),detail.getBuyNum(),detail.getAmount()};
            id=this.executeInsert(sql,param);
            detail.setId(id);
        } catch (Exception e) {
			this.closeResource();
            e.printStackTrace();
        }
    }

	@Override
	public void deleteById(OrderInfo detail) throws Exception {
		String sql = " delete from t_order_info where id = ? ";
		Object params[] = new Object[] { detail.getId() };
		try {
		this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

	@Override
	public OrderInfo selectById(Integer id) throws Exception {
		String sql = " select baseOrderId,goodsId,buyNum,amount from t_order_info where id = ? ";
		ResultSet resultSet = null;
		OrderInfo orderInfo = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				orderInfo = createEntityByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
		}
		return orderInfo;
	}

	@Override
	public List<OrderInfo> selectList(Integer baseOrderId)throws Exception {
		List<OrderInfo> orderInfoList = null;
		List<Object> paramsList=new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(" select id,baseOrderId,goodsId,buyNum,amount FROM t_order_info where 1=1 ");
		
		if(EmptyUtils.isNotEmpty(baseOrderId)){
			sql.append(" and baseOrderId=? ");
			paramsList.add(baseOrderId);
		}
		ResultSet resultSet = this.executeQuery(sql.toString(), paramsList.toArray());
		try {
			orderInfoList=new ArrayList<OrderInfo>();
			while (resultSet.next()) {
				OrderInfo orderInfol = this.createEntityByResultSet(resultSet);
				orderInfoList.add(orderInfol);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
		return orderInfoList;
	}

	@Override
	public Integer selectCount(OrderInfoParam params)throws Exception {
		Integer count = 0;
		String sql = " select count(*) FROM t_order_info ";
		ResultSet resultSet = this.executeQuery(sql, new Object[] {});
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
		return count;
	}
	@Override
	public OrderInfo createEntityByResultSet(ResultSet rs) throws Exception {
		OrderInfo orderInfol = new OrderInfo();
		orderInfol.setId(rs.getInt("id"));
		orderInfol.setBaseOrderId(rs.getInt("baseOrderId"));
		orderInfol.setGoods((Goods) goodsDao.selectById(rs.getInt("goodsId")));
		orderInfol.setGoodsId(rs.getInt("goodsId"));
		orderInfol.setBuyNum(rs.getInt("buyNum"));
		orderInfol.setAmount(rs.getFloat("amount"));
		return orderInfol;
	}
}
