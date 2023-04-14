package cn.interestingshop.service.goods;

import java.util.List;

import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.utils.ClassifyVo;

/**
 * Created by bdqn on 2016/5/8.
 */
public interface ClassifyService {
    /**
     * 根据id查询商品分类
     * @param id
     * @return
     */
    public Classify getById(Integer id);
    /**
     * 查询商品分类列表
     * @param params
     * @return
     */
    public List<Classify> getList(ClassifyParam params);
    /**
     * 查询数目
     * @param params
     * @return
     */
    public int getCount(ClassifyParam params);
    /**
     * 修改商品分类
     * @param params
     */
    public void update(Classify classify);
    /**
     * 添加商品分类
     * @param params
     */
    public void save(Classify classify);
    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(Integer id);
    /**
     * 查询全部的商品分类
     * @return
     */
    public List<ClassifyVo> getList();
}
