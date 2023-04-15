package cn.interestingshop.dao.order;

import java.util.List;

import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.param.OrderInfoParam;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoMapper {

    public void save(OrderInfo detail) throws Exception;

    public void deleteById(OrderInfo detail) throws Exception;

    public OrderInfo selectById(Integer id)throws Exception;

    public List<OrderInfo> selectList(Integer baseOrderId)throws Exception;

    public Integer selectCount(@Param("params") OrderInfoParam params)throws Exception;

}