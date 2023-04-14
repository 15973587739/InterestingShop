package cn.interestingshop.service.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.interestingshop.dao.order.UserAddressDao;
import cn.interestingshop.dao.order.UserAddressDaoImpl;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
import cn.interestingshop.utils.DataSourceUtil;

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
    public List<UserAddress> getList(Integer id) throws Exception{
        Connection connection = null;
        List<UserAddress> userAddressList = null;
        try {
            connection = DataSourceUtil.openConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            UserAddressParam params = new UserAddressParam();
            params.setUserId(id);
            userAddressList = userAddressDao.selectList(params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	DataSourceUtil.closeConnection(connection);
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
        Connection connection = null;
        Integer userAddressId = null;
        try {
            connection = DataSourceUtil.openConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            UserAddress userAddress=new UserAddress();
            userAddress.setUserId(id);
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddressId = userAddressDao.save(userAddress);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	DataSourceUtil.closeConnection(connection);
        }
        return userAddressId;
    }

    @Override
    public UserAddress getById(Integer id) {
        Connection connection = null;
        UserAddress userAddress= null;
        try {
            connection = DataSourceUtil.openConnection();
            UserAddressDao userAddressDao = new UserAddressDaoImpl(connection);
            userAddress = (UserAddress) userAddressDao.selectById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DataSourceUtil.closeConnection(connection);
            return  userAddress;
        }
    }
}
