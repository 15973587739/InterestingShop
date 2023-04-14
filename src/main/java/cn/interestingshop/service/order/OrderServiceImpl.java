package cn.interestingshop.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.interestingshop.dao.goods.GoodsDao;
import cn.interestingshop.dao.goods.GoodsDaoImpl;
import cn.interestingshop.dao.order.BaseOrderDao;
import cn.interestingshop.dao.order.BaseOrderDaoImpl;
import cn.interestingshop.dao.order.OrderInfoDao;
import cn.interestingshop.dao.order.OrderInfoDaoImpl;
import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.ShopCart;
import cn.interestingshop.utils.ShopGoods;
import cn.interestingshop.utils.StringUtils;

public class OrderServiceImpl implements OrderService {

    /**
     * 结算购物车物品包含以下步骤：
     * 1.生成订单
     * 2.生成订单明细
     * 3.更新商品表，减库存
     * 注意加入事物的控制
     */

    @Override
    public BaseOrder pay(User user, ShopCart shopCart, String address) {
        // TODO Auto-generated method stub
        Connection connection = null;
        BaseOrder baseOrder = new BaseOrder();
        try {
            connection = DataSourceUtil.openConnection();
            connection.setAutoCommit(false);
            GoodsDao goodsDao = new GoodsDaoImpl(connection);
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection);
            OrderInfoDao orderInfoDao = new OrderInfoDaoImpl(connection);
            //增加订单
            baseOrder.setUserId(user.getId());
            baseOrder.setAccount(user.getAccount());
            baseOrder.setUserAddress(address);
            baseOrder.setCreateTime(new Date());
            baseOrder.setAmount(shopCart.getAmount());
            baseOrder.setOrderNo(StringUtils.randomUUID());
            orderDao.save(baseOrder);
            //增加订单对应的明细信息
            for (ShopGoods shopGoods : shopCart.getShopGoodsList()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setBaseOrderId(baseOrder.getId());
                orderInfo.setAmount(shopGoods.getAmount());
                orderInfo.setGoods(shopGoods.getGoods());
                orderInfo.setBuyNum(shopGoods.getBuyNum());
                orderInfoDao.save(orderInfo);
                //更新商品表的库存
                goodsDao.updateStock(shopGoods.getGoods().getId(), shopGoods.getBuyNum());
                connection.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            baseOrder = null;
        } finally {
            try {
                connection.setAutoCommit(true);
                DataSourceUtil.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return baseOrder;
    }

    @Override
    public List<BaseOrder> getBaseList(Integer userId, Integer currentPageNo, Integer pageSize) {
        Connection connection = null;
        List<BaseOrder> orderList = new ArrayList<BaseOrder>();
        try {
            connection = DataSourceUtil.openConnection();
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection);
            OrderInfoDao orderInfoDao=new OrderInfoDaoImpl(connection);
            orderList = orderDao.selectList(userId, currentPageNo, pageSize);
            for(BaseOrder baseOrder:orderList){
            	baseOrder.setOrderInfoList(orderInfoDao.selectList(baseOrder.getId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return orderList;
    }

    @Override
    public int count(Integer userId) {
        Connection connection = null;
        Integer count=0;
        try {
            connection = DataSourceUtil.openConnection();
            BaseOrderDao orderDao = new BaseOrderDaoImpl(connection);
            count=orderDao.selectCount(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return count;
    }

    /**
     * 调用dao接口：OrderInfoMapper的方法实现
     */
    @Override
    public List<OrderInfo> getOrderInfoList(Integer orderId) {
        Connection connection = null;
        List<OrderInfo> OrderInfoList = new ArrayList<OrderInfo>();
        try {
            connection = DataSourceUtil.openConnection();
            OrderInfoDao OrderInfoDao = new OrderInfoDaoImpl(connection);
            OrderInfoList = OrderInfoDao.selectList(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return OrderInfoList;
    }
}
