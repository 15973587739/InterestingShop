package cn.interestingshop.service.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.interestingshop.dao.goods.GoodsMapper;
import cn.interestingshop.dao.notice.NoticeMapper;
import cn.interestingshop.dao.order.BaseOrderMapper;
import cn.interestingshop.dao.order.OrderInfoMapper;
import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.*;
import org.apache.ibatis.session.SqlSession;

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
        SqlSession sqlSession=null;
        BaseOrder baseOrder = new BaseOrder();
        try {
            sqlSession = MyBatisUtil.createSqlSession();
            //增加订单
            baseOrder.setUserId(user.getId());
            baseOrder.setAccount(user.getAccount());
            baseOrder.setUserAddress(address);
            baseOrder.setCreateTime(new Date());
            baseOrder.setAmount(shopCart.getAmount());
            baseOrder.setOrderNo(StringUtils.randomUUID());
            sqlSession.getMapper(BaseOrderMapper.class).save(baseOrder);
            //增加订单对应的明细信息
            for (ShopGoods shopGoods : shopCart.getShopGoodsList()) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setBaseOrderId(baseOrder.getId());
                orderInfo.setAmount(shopGoods.getAmount());
                orderInfo.setGoods(shopGoods.getGoods());
                orderInfo.setBuyNum(shopGoods.getBuyNum());
                sqlSession.getMapper(OrderInfoMapper.class).save(orderInfo);
                //更新商品表的库存
                sqlSession.getMapper(GoodsMapper.class).updateStock(shopGoods.getGoods().getId(), shopGoods.getBuyNum());
                sqlSession.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            sqlSession.rollback();
            baseOrder = null;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return baseOrder;
    }

    @Override
    public List<BaseOrder> getBaseList(Integer userId, Integer currentPageNo, Integer pageSize) {
        SqlSession sqlSession=null;
        List<BaseOrder> orderList = new ArrayList<BaseOrder>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            orderList = sqlSession.getMapper(BaseOrderMapper.class).selectList(userId,currentPageNo,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return orderList;
    }

    @Override
    public int count(Integer userId) {
        int count = 0;
        SqlSession sqlSession=null;
        try {
            sqlSession= MyBatisUtil.createSqlSession();
            count = sqlSession.getMapper(BaseOrderMapper.class).selectCount(userId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return count;
    }

    /**
     * 调用dao接口：OrderInfoMapper的方法实现
     */
    @Override
    public List<OrderInfo> getOrderInfoList(Integer orderId) {
        SqlSession sqlSession=null;
        List<OrderInfo> OrderInfoList = new ArrayList<OrderInfo>();
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            OrderInfoList = sqlSession.getMapper(OrderInfoMapper.class).selectList(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return OrderInfoList;
    }
}
