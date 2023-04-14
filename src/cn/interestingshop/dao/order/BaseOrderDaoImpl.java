package cn.interestingshop.dao.order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.utils.EmptyUtils;
/**
 * 订单dao的实现类
 */
public class BaseOrderDaoImpl extends BaseDaoImpl implements BaseOrderDao {

	public BaseOrderDaoImpl(Connection connection) {
		super(connection);
	}

	/**
	 * 保存订单
	 * @param order
	 * @throws java.sql.SQLException
	 */
	public void save(BaseOrder baseOrder) {//保存订单
		Integer id=0;
		String sql="insert into t_base_order(userId,account,userAddress,createTime,amount,orderNo) values(?,?,?,?,?,?) ";
		Object[] param=new Object[]{baseOrder.getUserId(),baseOrder.getAccount(),baseOrder.getUserAddress(),new Date(),baseOrder.getAmount(),baseOrder.getOrderNo()};
		try {
			id=this.executeInsert(sql, param);
			baseOrder.setId(new Integer(id).intValue());
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

	@Override
	public void deleteById(Integer id) {
		String sql = " delete from t_base_order where id = ? ";
		Object params[] = new Object[] { id };
		try {
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

	@Override
	public BaseOrder selectById(Integer id) {
		String sql = " select * from t_base_order where id = ? ";
		ResultSet resultSet = null;
		BaseOrder baseOrder = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				baseOrder = createEntityByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
		}
		return baseOrder;
	}

	@Override
	public List<BaseOrder> selectList(Integer userId,Integer pageIndex,Integer pageSize) {
		List<Object> paramsList=new ArrayList<Object>();   
		List<BaseOrder> orderList=new ArrayList<BaseOrder>();
		StringBuffer sql=new StringBuffer(" select id,userId,account,userAddress,createTime,amount,orderNo from t_base_order  where 1=1 ");
		if(EmptyUtils.isNotEmpty(userId)){
			sql.append(" and userId = ? ");
			paramsList.add(userId);
		}
//		int total = count(userId);
//		Pager pager = new Pager(total, pageSize, currentPageNo);
		sql.append(" limit  " + (pageIndex - 1) * pageSize + "," + pageSize);
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				BaseOrder baseOrder = this.createEntityByResultSet(resultSet);
				orderList.add(baseOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
				
		return orderList;
	}

	@Override
	public Integer selectCount(Integer userId)  {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count=0;
		StringBuffer sql=new StringBuffer(" select count(id) count from t_base_order  where 1=1 ");
		if(EmptyUtils.isNotEmpty(userId)){
			sql.append(" and userId = ? ");
			paramsList.add(userId);
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return count;
	}
	@Override
	public BaseOrder createEntityByResultSet(ResultSet rs) throws Exception {
		BaseOrder baseOrder = new BaseOrder();
		baseOrder.setId(rs.getInt("id"));
		baseOrder.setUserId(rs.getInt("userId"));
		baseOrder.setCreateTime(rs.getDate("createTime"));
		baseOrder.setAmount(rs.getFloat("amount"));
		baseOrder.setUserAddress(rs.getString("userAddress"));
		baseOrder.setOrderNo(rs.getString("orderNo"));
		baseOrder.setAccount(rs.getString("account"));
		return baseOrder;
	}
}
