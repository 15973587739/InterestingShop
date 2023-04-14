package cn.interestingshop.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.User;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;

/**
 * 用户dao
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    /**
     * 保存用户
     *
     * @param user
     * @throws java.sql.SQLException
     */
    public int save(User user){//新增用户信息
    	Integer id=0;
    	try {
    		String sql=" INSERT into t_user(account,nickName,password,gender,idCardNo,email,phone) values(?,?,?,?,?,?,?) ";
            try {
                Object param[]=new Object[]{user.getAccount(),user.getNickName(),user.getPassword(),user.getGender(),user.getIdCardNo(),user.getEmail(),user.getPhone()};
                id=this.executeInsert(sql,param);
                user.setId(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }
    	return id;
    }

    //更新用户信息
    public int update(User user) {
		Integer count=0;
		try {
        	Object[] params = new Object[] {user.getAccount(),user.getNickName(),user.getType(),user.getGender(),user.getIdCardNo(),user.getEmail(),user.getPhone(),user.getId()};
        	String sql = " UPDATE t_user SET account=?,nickName =?,type=?,gender =?, idCardNo =?, email =?, phone =? WHERE id =?  ";
			count=this.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }
		return count;
    }

	@Override
	public int deleteById(String id) {
		Integer count=0;
		String sql = " delete from t_user where id = ? ";
		Object params[] = new Object[] { id };
		try{
			this.executeUpdate(sql.toString(), params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
        	this.closeResource();
		}
		return count;
	}

	@Override
	public List<User> selectList(Integer currentPageNo,Integer pageSize) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<User> userList=new ArrayList<User>();
		StringBuffer sql=new StringBuffer("  select id,account,password,nickName,gender,idCardNo,email,phone,type from t_user where 1=1 ");
		ResultSet resultSet = null;
		try {
			int total = selectCount();
			Pager pager = new Pager(total, pageSize, currentPageNo);
			sql.append(" limit  " + (pager.getCurrentPage() - 1) * pager.getRowPerPage() + "," + pager.getRowPerPage());
			resultSet=this.executeQuery(sql.toString(),paramsList.toArray());
			while (resultSet.next()) {
				User user = this.createEntityByResultSet(resultSet);
				userList.add(user);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return userList;
	}
	
	public Integer selectCount() throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		StringBuffer sql=new StringBuffer(" select count(*) count from t_user where 1=1 ");
		Integer count=0;
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count=resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return count;
	}
	
	
	@Override
	public User selectById(Integer id,String account) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<User> userList=new ArrayList<User>();
		StringBuffer sql=new StringBuffer("  select id,account,nickName,password,gender,idCardNo,email,phone,type from t_user where 1=1  ");

		if(EmptyUtils.isNotEmpty(id)){
			sql.append(" and id=? ");
			paramsList.add(id);
		}

		if(EmptyUtils.isNotEmpty(account)){
			sql.append(" and account=? ");
			paramsList.add(account);
		}

		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		User user=null;
		try {
			while (resultSet.next()) {
				user = this.createEntityByResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		return user;
	}

	@Override
	public User createEntityByResultSet(ResultSet rs) throws Exception {
		User user = new User();
		user.setAccount(rs.getString("account"));
		user.setNickName(rs.getString("nickName"));
		user.setPassword(rs.getString("password"));
		user.setGender(rs.getInt("gender"));
		user.setIdCardNo(rs.getString("idCardNo"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setType(rs.getInt("type"));
		user.setId(rs.getInt("id"));
		return user;
	}
}
