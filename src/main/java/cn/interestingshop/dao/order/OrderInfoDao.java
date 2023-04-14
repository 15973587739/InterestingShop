package cn.interestingshop.dao.order;
import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.param.OrderInfoParam;

/**
 * 订单详细
 * Created by bdqn on 2016/5/8.
 */
public interface OrderInfoDao extends IBaseDao {

    public void save(OrderInfo detail) throws Exception;

	public void deleteById(OrderInfo detail) throws Exception;
	
	public OrderInfo selectById(Integer id)throws Exception;
	
	public List<OrderInfo> selectList(Integer baseOrderId)throws Exception;
	
	public Integer selectCount(OrderInfoParam params)throws Exception;
}
