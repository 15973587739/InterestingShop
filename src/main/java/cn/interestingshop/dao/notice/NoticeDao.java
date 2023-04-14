package cn.interestingshop.dao.notice;

import java.util.List;

import cn.interestingshop.dao.IBaseDao;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;

/**
 * 新闻的dao
 */
public interface NoticeDao extends IBaseDao {
	/**
	 * 添加新闻
	 * @param Notice
	 * @throws Exception
	 */
	public void save(Notice Notice) throws Exception;
	/**
	 * 修改新闻
	 * @param Notice
	 * @throws Exception
	 */
	public void update(Notice Notice) throws Exception;
	/**
	 * 根据id删除新闻
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Integer id) throws Exception;
	/**
	 * 根据id查询新闻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Notice selectById(Integer id)throws Exception;
	/**
	 * 查询新闻列表
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Notice> selectList(NoticeParams params)throws Exception;
	/**
	 * 查询新闻的数目
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Integer selectCount(NoticeParams params)throws Exception;
}
