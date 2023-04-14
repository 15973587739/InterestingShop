package cn.interestingshop.service.user;
import java.util.List;

import cn.interestingshop.entity.UserAddress;

/**
 * Created by bdqn on 2016/5/12.
 */
public interface UserAddressService {
    /**
     * 根据loginName 查询用户地址
     * @param id
     * @return
     * @throws Exception
     */
    public List<UserAddress> getList(Integer id) throws Exception;
    /**
     * 给用户添加地址
     * @param id
     * @param address
     * @return
     */
    public Integer save(Integer id, String address, String remark) throws Exception;
    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    public UserAddress getById(Integer id);

}
