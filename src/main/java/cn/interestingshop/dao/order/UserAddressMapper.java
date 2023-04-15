package cn.interestingshop.dao.order;


import java.util.List;
import cn.interestingshop.entity.UserAddress;
import cn.interestingshop.param.UserAddressParam;
public interface UserAddressMapper {
    public List<UserAddress> selectList(UserAddressParam param);

    public Integer save(UserAddress userAddress);

    public UserAddress selectById(Integer addressId);
}