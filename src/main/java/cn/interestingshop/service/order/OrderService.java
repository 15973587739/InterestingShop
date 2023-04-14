package cn.interestingshop.service.order;
import java.util.List;

import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.ShopCart;

/**
 * OrderService接口方法：
 * （1）结算订单（返回类型：Order对象，参数：ShopCart对象、User对象、收货地址）。
 * （2）根据查询条件，分页显示订单信息列表（返回类型：List<BaseOrder>，参数：当前页码、页码容量，用户id）。
 * （3）根据条件查询订单表总记录数（返回类型：int，参数：用户id）。
 * （4）根据订单id查询订单明细列表（返回类型：List<OrderInfo>，参数：订单id）。
 *
 */
public interface OrderService {
	
	public BaseOrder pay(User user, ShopCart cart, String address);
	
	public List<BaseOrder> getBaseList(Integer userId,Integer pageIndex,Integer pageSize);

    public int count(Integer userId);
    
    public List<OrderInfo> getOrderInfoList(Integer baseOrderId);

}
