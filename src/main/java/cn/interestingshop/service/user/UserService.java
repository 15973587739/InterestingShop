package cn.interestingshop.service.user;
import java.util.List;

import cn.interestingshop.entity.User;

public interface UserService {

	public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean deleteById(Integer userId);
	
	public User getById(Integer userId,String loginName);
	
	public List<User> getList(Integer currentPageNo,Integer pageSize);
	
	public int getCount();
}
