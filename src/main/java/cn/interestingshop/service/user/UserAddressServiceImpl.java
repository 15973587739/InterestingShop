package cn.interestingshop.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.interestingshop.dao.order.UserAddressMapper;
import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.entity.User;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAddressServiceImpl implements UserAddressService {
    /**
     * 查询用户地址列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<UserAddress> getList(Integer id) throws Exception{
        SqlSession sqlSession=null;
        List<UserAddress> userAddressList=null;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            UserAddressParam params = new UserAddressParam();
            params.setUserId(id);
            userAddressList = sqlSession.getMapper(UserAddressMapper.class).selectList(params);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return userAddressList;
    }
    /**
     * 添加用户地址
     *
     * @param id
     * @param address
     * @return
     */
    @Override
    public Integer save(Integer id, String address,String remark) {
        SqlSession session = null;
        Integer userAddressId = null;
        try {
            session = MyBatisUtil.createSqlSession();
            UserAddress userAddress=new UserAddress();
            userAddress.setUserId(id);
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddressId =session.getMapper(UserAddressMapper.class).save(userAddress);
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }finally{
            MyBatisUtil.closeSqlSession(session);
        }
        return userAddressId;
    }

    @Override
    public UserAddress getById(Integer id) {
        UserAddress userAddress=new UserAddress();
        SqlSession sqlSession=null;
        try {
            sqlSession=MyBatisUtil.createSqlSession();
            userAddress = sqlSession.getMapper(UserAddressMapper.class).selectById(id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        return userAddress;
    }
}
