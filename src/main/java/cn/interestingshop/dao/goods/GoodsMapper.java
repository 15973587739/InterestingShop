package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.entity.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    Integer updateStock(@Param("id") Integer id, @Param("buyNum") Integer buyNum) throws Exception;

    public Integer save(Goods goods) throws Exception;

    public Integer update(Goods goods) throws Exception;

    public Integer deleteById(Integer id) throws Exception;

    public Goods selectById(Integer id)throws Exception;

    public List<Goods> selectList(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize, @Param("goodsName") String goodsName, @Param("categoryId") Integer categoryId)throws Exception;

    public Integer selectCount(@Param("goodsName") String goodsName, @Param("categoryId") Integer categoryId)throws Exception;

}