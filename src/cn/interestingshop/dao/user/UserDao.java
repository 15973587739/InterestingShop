package cn.interestingshop.dao.user;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.User;

/***
 * UserDao 用户业务的dao层
 * 从父类继承下的被使用的方法
 * User getById(userId);
 * Integer userDao.getRowCount(params);
 * List<User> userDao.getRowList(params);
 */
public interface UserDao extends IBaseDao {

	int save(User user) throws Exception;//新增用户信息

	int update(User user) throws Exception;//更新用户信息

	public int deleteById(String id) throws Exception;
	
	public List<User> selectList(Integer currentPageNo,Integer pageSize)throws Exception;
	
	public Integer selectCount() throws Exception;
	
	public User selectById(Integer id,String account) throws Exception;
}
