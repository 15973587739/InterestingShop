package cn.interestingshop.dao.notice;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.interestingshop.dao.BaseDaoImpl;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.utils.EmptyUtils;

public class NoticeDaoImpl extends BaseDaoImpl implements NoticeDao {

	public NoticeDaoImpl(Connection connection) {
		super(connection);
	}


	public void save(Notice Notice) throws Exception {// 保存新闻
		String sql = " INSERT into t_notice(title,content,createTime) values( ?, ?, ?) ";
		Object[] params = new Object[] { Notice.getTitle(), Notice.getContent(),
				new Date() };
		this.executeUpdate(sql, params);
	}

	public void update(Notice Notice) throws Exception {// 更新新闻
		String sql = " update t_notice set title=?,content=? where id=? ";
		Object[] params = new Object[] {Notice.getTitle(), Notice.getContent(),Notice.getId() };
		this.executeUpdate(sql, params);
	}

	public void deleteById(Integer id) throws Exception {
		String sql = " delete from t_notice where id = ? ";
		Object params[] = new Object[] { id };
		this.executeUpdate(sql.toString(), params);
	}

	public Notice selectById(Integer id) {
		String sql = " select * from t_notice where id = ? ";
		ResultSet resultSet = null;
		Notice Notice = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				Notice = createEntityByResultSet(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
		}
		return Notice;
	}

	@Override
	public List<Notice> selectList(NoticeParams params) {
		List<Object> paramsList=new ArrayList<Object>();   
		List<Notice> noticeList=new ArrayList<Notice>();
		StringBuffer sql=new StringBuffer(" select id,title,content,createTime FROM t_notice where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getTitle())){
			sql.append(" and title like ? ");
			paramsList.add(params.getTitle());
		}
		if(EmptyUtils.isNotEmpty(params.getSort())){
			sql.append(" order by " + params.getSort()+" ");
		}
		if(params.isPage()){
			sql.append(" limit  " + params.getStartIndex() + "," + params.getPageSize());
		}
		
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				Notice Notice = this.createEntityByResultSet(resultSet);
				noticeList.add(Notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
				
		return noticeList;
	}
	
	@Override
	public Integer selectCount(NoticeParams params) {
		List<Object> paramsList=new ArrayList<Object>();   
		Integer count = 0;
		StringBuffer sql=new StringBuffer(" select count(*) as count FROM t_notice where 1=1 ");
		if(EmptyUtils.isNotEmpty(params.getTitle())){
			sql.append(" and title like ? ");
			paramsList.add(params.getTitle());
		}
		ResultSet resultSet = this.executeQuery(sql.toString(),paramsList.toArray());
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
		return count;
	}

	
	@Override
	public Notice createEntityByResultSet(ResultSet rs) throws Exception {
		Notice Notice = new Notice();
		Notice.setId(rs.getInt("id"));
		Notice.setTitle(rs.getString("title"));
		Notice.setContent(rs.getString("content"));
		Notice.setCreateTime(rs.getDate("createTime"));
		return Notice;
	}
}
