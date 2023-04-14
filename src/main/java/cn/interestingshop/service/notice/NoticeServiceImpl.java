package cn.interestingshop.service.notice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.interestingshop.dao.notice.NoticeDao;
import cn.interestingshop.dao.notice.NoticeDaoImpl;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.utils.DataSourceUtil;

/**
 *
 */
public class NoticeServiceImpl implements NoticeService {

	public void deleteById(String id) {// 删除新闻
		Connection connection = null;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			noticeDao.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
	}

	public Notice getById(String id) {// 根据ID获取新闻
		Notice notice = null;
		Connection connection = null;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			notice = noticeDao.selectById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return notice;
	}

	public void save(Notice notice) {// 保存新闻
		Connection connection = null;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			noticeDao.save(notice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
	}

	public void update(Notice notice) {// 更新留言
		Connection connection = null;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			noticeDao.update(notice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
	}

	public List<Notice> queryNoticePageList(NoticeParams param) throws SQLException {
		List<Notice> noticeList = new ArrayList<Notice>();
		Connection connection = null;
		NoticeDao noticeDao = null;
		try {
			connection = DataSourceUtil.openConnection();
			noticeDao = new NoticeDaoImpl(connection);
			noticeList = noticeDao.selectList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(connection.isClosed());
			DataSourceUtil.closeConnection(connection);
		}
		return noticeList;
	}

	@Override
	public List<Notice> getList(NoticeParams param) {
		List<Notice> noticeList = new ArrayList<Notice>();
		Connection connection = null;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			noticeList = noticeDao.selectList(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return noticeList;
	}

	@Override
	public Integer getCount(NoticeParams param) {
		Connection connection = null;
		Integer count = 0;
		try {
			connection = DataSourceUtil.openConnection();
			NoticeDao noticeDao = new NoticeDaoImpl(connection);
			count = noticeDao.selectCount(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
		}
		return count;
	}

}
