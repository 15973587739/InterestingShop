package cn.interestingshop.dao.order;

import java.util.List;

import cn.interestingshop.entity.BaseOrder;
import org.apache.ibatis.annotations.Param;

public interface BaseOrderMapper {

    public void save(BaseOrder baseOrder) ;

    public void deleteById(Integer id);

    public BaseOrder selectById(Integer id) ;

    public List<BaseOrder> selectList(@Param("userId") Integer userId, @Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize) ;

    public Integer selectCount(@Param("userId") Integer userId);

}