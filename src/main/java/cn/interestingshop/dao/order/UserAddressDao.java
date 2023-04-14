package cn.interestingshop.dao.order;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;

/**
 * Created by bdqn on 2016/5/12.
 * addObject(UserAddress userAddress)
 * getRowList(params)
 */
public interface UserAddressDao extends IBaseDao {
	
	public List<UserAddress> selectList(UserAddressParam param);
	
	public Integer save(UserAddress userAddress);
	
	public UserAddress selectById(Integer addressId);

}
