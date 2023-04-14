package cn.interestingshop.utils;

import java.io.Serializable;
import java.util.List;

import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;

/**
 * Created by bdqn on 2016/5/6.
 */
public class OrderVo implements Serializable {
    private BaseOrder baseOrder;
    private List<OrderInfo> orderInfoList;

    public BaseOrder getOrder() {
        return baseOrder;
    }

    public void setOrder(BaseOrder baseOrder) {
        this.baseOrder = baseOrder;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
