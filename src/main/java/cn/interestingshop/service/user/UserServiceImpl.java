package cn.interestingshop.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import cn.interestingshop.entity.User;
import cn.interestingshop.utils.DataSourceUtil;

public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public boolean save(User user){
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(UserMapper.class).save(user)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public boolean update(User user) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(UserMapper.class).update(user)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public boolean deleteById(Integer userId) {
		SqlSession session = null;
		Boolean flag = false;
		try {
			session = MyBatisUtil.createSqlSession();
			if (session.getMapper(UserMapper.class).deleteById(userId)>0){
				flag = true;
				session.commit();
			}
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
			flag = false;
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
		return flag;
	}

	@Override
	public User getById(Integer userId, String loginName) {
		User user=null;
		SqlSession sqlSession=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			user = sqlSession.getMapper(UserMapper.class).selectById(userId, loginName);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return user;
	}

	@Override
	public List<User> getList(Integer currentPageNo, Integer pageSize) {
		SqlSession sqlSession=null;
		List<User> userList=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			userList = sqlSession.getMapper(UserMapper.class).selectList(currentPageNo, pageSize);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return userList;
	}

	@Override
	public int getCount() {
		int count = 0;
		SqlSession sqlSession=null;
		try {
			sqlSession= MyBatisUtil.createSqlSession();
			 count = sqlSession.getMapper(UserMapper.class).selectCount();

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}
}
