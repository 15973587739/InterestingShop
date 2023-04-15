package cn.interestingshop.service.notice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.notice.NoticeMapper;
import cn.interestingshop.dao.user.UserMapper;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.utils.DataSourceUtil;
import cn.interestingshop.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 */
public class NoticeServiceImpl implements NoticeService {

	@Override
	public void deleteById(Integer id) {// 删除新闻
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSqlSession();
			session.getMapper(NoticeMapper.class).deleteById(id);
			session.commit();
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
	}
	@Override
	public Notice getById(Integer id) {// 根据ID获取新闻
		Notice notice = null;
		SqlSession sqlSession=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			notice = sqlSession.getMapper(NoticeMapper.class).selectById(id);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return notice;
	}


	@Override
	public void save(Notice notice) {// 保存新闻
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSqlSession();
			session.getMapper(NoticeMapper.class).save(notice);
			session.commit();
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
	}

	public void update(Notice notice) {// 更新留言
		SqlSession session = null;
		try {
			session = MyBatisUtil.createSqlSession();
			session.getMapper(NoticeMapper.class).update(notice);
			session.commit();
		}catch (Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			MyBatisUtil.closeSqlSession(session);
		}
	}

	@Override
	public List<Notice> getList(NoticeParams param) {
		SqlSession sqlSession=null;
		List<Notice> noticeList = new ArrayList<Notice>();
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			noticeList = sqlSession.getMapper(NoticeMapper.class).selectList(param);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return noticeList;
	}

	@Override
	public Integer getCount(NoticeParams param) {
		int count = 0;
		SqlSession sqlSession=null;
		try {
			sqlSession= MyBatisUtil.createSqlSession();
			count = sqlSession.getMapper(NoticeMapper.class).selectCount(param);

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		return count;
	}

}
