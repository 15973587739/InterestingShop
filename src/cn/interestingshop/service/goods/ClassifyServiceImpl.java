package cn.interestingshop.service.goods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.goods.ClassifyDao;
import cn.interestingshop.dao.goods.ClassifyDaoImpl;
import cn.interestingshop.entity.Classify;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.utils.ClassifyVo;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.EmptyUtils;

/**
 * Created by bdqn on 2016/5/8.
 */
public class ClassifyServiceImpl implements ClassifyService {
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Classify getById(Integer id) {
        Connection connection = null;
        Classify classify = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            classify =classifyDao.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return classify;
    }

    @Override
    public List<Classify> getList(ClassifyParam params) {
        Connection connection = null;
        List<Classify> rtn = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            rtn = classifyDao.selectList(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return rtn;
    }

    @Override
    public int getCount(ClassifyParam params) {
        Connection connection = null;
        int rtn = 0;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            rtn = classifyDao.selectCount(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return rtn;
    }

    @Override
    public void update(Classify classify) {
        Connection connection = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            classifyDao.update(classify);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
    }
    /**
     * 新增商品分类
     */
    @Override
    public void save(Classify classify) {
        Connection connection = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            classifyDao.save(classify);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
    }
    /**
     * 根据Id删除商品
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        Connection connection = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            classifyDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
    }
    /**
     * 查询全部的商品分类
     * @return
     */
    @Override
    public List<ClassifyVo> getList() {
        //查询一级分类的列表
        List<ClassifyVo> classify1VoList = new ArrayList<ClassifyVo>();
        //查询一级分类
        List<Classify> classify1List = getGoodsCategories(null);
        //查询二级分类
        for (Classify product1Category : classify1List) {
            //封装一级分类
            ClassifyVo classifyVo = new ClassifyVo();
            classifyVo.setClassify(product1Category);
            List<ClassifyVo> classifyVo1ChildList = new ArrayList<ClassifyVo>();
            //根据一级分类查询二级分类
            List<Classify> classify2List = getGoodsCategories(product1Category.getId());
            for (Classify classify2 : classify2List) {
                ClassifyVo classifyVo2 = new ClassifyVo();
                classifyVo1ChildList.add(classifyVo2);
                classifyVo2.setClassify(classify2);
                List<ClassifyVo> classifyVo2ChildList = new ArrayList<ClassifyVo>();
                classifyVo2.setClassifyVoList(classifyVo2ChildList);
                //根据二级分类查询三级分类的列表
                List<Classify> classify3List = getGoodsCategories(classify2.getId());
                for (Classify classify3 : classify3List) {
                    ClassifyVo classifyVo3 = new ClassifyVo();
                    classifyVo3.setClassify(classify3);
                    classifyVo2ChildList.add(classifyVo3);
                }
            }
            classifyVo.setClassifyVoList(classifyVo1ChildList);
            classify1VoList.add(classifyVo);
        }
        return classify1VoList;
    }
    /**
     * 查询子分类
     * @param parentId
     * @return
     */
    private List<Classify> getGoodsCategories(Integer parentId) {//根据父ID查询所有子商品分类
        Connection connection = null;
        List<Classify> classifyList = null;
        try {
            connection = DataSourceUtil.openConnection();
            ClassifyDao classifyDao = new ClassifyDaoImpl(connection);
            ClassifyParam params = new ClassifyParam();
            if (EmptyUtils.isNotEmpty(parentId)) {
            	params.setParentId(parentId);
            } else {
            	params.setParentId(0);
            }
            classifyList = classifyDao.selectList(params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceUtil.closeConnection(connection);
        }
        return classifyList;
    }
}
