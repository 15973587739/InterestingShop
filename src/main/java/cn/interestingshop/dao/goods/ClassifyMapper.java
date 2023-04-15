package cn.interestingshop.dao.goods;

import java.util.List;

import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import org.apache.ibatis.annotations.Param;

public interface ClassifyMapper {

    /**
     * 根据id删除商品
     * @param parseLong
     */
    void deleteById(Integer parseLong);//删除商品分类
    /**
     * 根据条件查询商品列表
     * @param param
     */
    public List<Classify> selectList(@Param("param") ClassifyParam param);
    /**
     * 根据id查询商品分类
     * @param id
     */
    Classify selectById(Integer id);
    /**
     * 添加商品分类
     * @param classify
     */
    public Integer save(Classify classify) ;
    /**
     * 根据参数查询商品分类的数目
     * @param param
     */
    public Integer selectCount(ClassifyParam param);
    /**
     * 修改商品分类
     * @param classify
     */
    public void update(Classify classify) ;


}