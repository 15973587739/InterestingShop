package cn.interestingshop.service.goods;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.goods.ClassifyMapper;
import cn.interestingshop.dao.notice.NoticeMapper;
import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.entity.Classify;
import cn.interestingshop.entity.User;
import cn.interestingshop.param.ClassifyParam;
import cn.interestingshop.utils.ClassifyVo;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

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
        Classify classify = null;
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            classify = sqlSession.getMapper(ClassifyMapper.class).selectById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return classify;
    }

    @Override
    public List<Classify> getList(ClassifyParam params) {
        List<Classify> rtn = null;
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            rtn = sqlSession.getMapper(ClassifyMapper.class).selectList(params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return rtn;
    }

    @Override
    public int getCount(ClassifyParam params) {
        int count = 0;
        SqlSession sqlSession=null;
        try {
            sqlSession= MyBatisUtil.createSqlSession();
            count = sqlSession.getMapper(ClassifyMapper.class).selectCount(params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return count;
    }

    @Override
    public void update(Classify classify) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSqlSession();
            session.getMapper(ClassifyMapper.class).update(classify);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally{
            MyBatisUtil.closeSqlSession(session);
        }
    }
    /**
     * 新增商品分类
     */
    @Override
    public void save(Classify classify) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSqlSession();
            session.getMapper(ClassifyMapper.class).save(classify);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally{
            MyBatisUtil.closeSqlSession(session);
        }
    }
    /**
     * 根据Id删除商品
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSqlSession();
            session.getMapper(ClassifyMapper.class).deleteById(id);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally{
            MyBatisUtil.closeSqlSession(session);
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
        List<Classify> rtn = null;
        SqlSession sqlSession=null;
        try {
            ClassifyParam params = new ClassifyParam();
            sqlSession=MyBatisUtil.createSqlSession();
            if (EmptyUtils.isNotEmpty(parentId)) {
                params.setParentId(parentId);
            } else {
                params.setParentId(0);
            }
            rtn = sqlSession.getMapper(ClassifyMapper.class).selectList(params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return rtn;
    }
}
