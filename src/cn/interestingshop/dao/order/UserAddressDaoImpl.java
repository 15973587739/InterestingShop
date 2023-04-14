package cn.interestingshop.dao.order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
import cn.interestingshop.utils.EmptyUtils;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddressDaoImpl extends BaseDaoImpl implements UserAddressDao {

    public UserAddressDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public UserAddress createEntityByResultSet(ResultSet rs) throws Exception {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(rs.getInt("id"));
        userAddress.setAddress(rs.getString("address"));
        userAddress.setUserId(rs.getInt("userId"));
        userAddress.setCreateTime(rs.getDate("createTime"));
        userAddress.setRemark(rs.getString("remark"));
        return userAddress;
    }

	@Override
	public List<UserAddress> selectList(UserAddressParam params) {
		List<Object> paramsList=new ArrayList<Object>();   
		List<UserAddress> userAddresseList=new ArrayList<UserAddress>();
		StringBuffer sql=new StringBuffer("  select id,userId,address,createTime,isDefault,remark from t_user_address  where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getUserId())){
			sql.append(" and userId = ? ");
			paramsList.add(params.getUserId());
		}
		if(EmptyUtils.isNotEmpty(params.getAddress())){
			sql.append(" and address like ? ");
			paramsList.add("%"+params.getAddress()+"%");
		}
		if(EmptyUtils.isNotEmpty(params.getSort())){
			sql.append(" order by " + params.getSort()+" ");
		}
		if(params.isPage()){
			sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				UserAddress userAddress = this.createEntityByResultSet(resultSet);
				userAddresseList.add(userAddress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return userAddresseList;
	}

	@Override
	public Integer save(UserAddress userAddress) {
		Integer id=0;
		String sql=" INSERT into t_user_address(userId,address,createTime,isDefault,remark) VALUES(?,?,?,?,?) ";
        try {
            Object param[]=new Object[]{userAddress.getUserId(),userAddress.getAddress(),new Date(),0,userAddress.getRemark()};
            id=this.executeInsert(sql,param);
            userAddress.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }
        return id;
	}

	@Override
	public UserAddress selectById(Integer addressId) {
		List<Object> paramsList=new ArrayList<Object>();   
		StringBuffer sql=new StringBuffer(" select id,userId,address,createTime,isDefault,remark from t_user_address  where id=? ");
		UserAddress userAddress =null;
		ResultSet resultSet = this.executeQuery(sql.toString(),new Object[]{addressId});
		try {
			while (resultSet.next()) {
				userAddress= this.createEntityByResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return userAddress;
	}
}
