package cn.interestingshop.dao.user;

import cn.interestingshop.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    int save(User user) throws Exception;//新增用户信息

    int update(User user) throws Exception;//更新用户信息

    public int deleteById(Integer id) throws Exception;

    public List<User> selectList(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize)throws Exception;

    public Integer selectCount() throws Exception;

    public User selectById(Integer id,String account) throws Exception;

}