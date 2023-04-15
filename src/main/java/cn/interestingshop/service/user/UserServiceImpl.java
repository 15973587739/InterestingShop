package cn.interestingshop.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import cn.interestingshop.entity.User;
import cn.interestingshop.utils.DataSourceUtil;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public boolean save(User user){
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return  count>0;
	}

	@Override
	public boolean update(User user) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return  count>0;
	}

	@Override
	public boolean deleteById(Integer userId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.deleteById(userId+"");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return  count>0;
	}

	@Override
	public User getById(Integer userId, String loginName) {
		Connection connection = null;
		User user=null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			user=userDao.selectById(userId,loginName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public List<User> getList(Integer currentPageNo, Integer pageSize) {
		Connection connection = null;
		List<User> userList=null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			userList=userDao.selectList(currentPageNo,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return userList;
	}

	@Override
	public int getCount() {
		Connection connection = null;
		Integer count=null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.selectCount();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}
}
