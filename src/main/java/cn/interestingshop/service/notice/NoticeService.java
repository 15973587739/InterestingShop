package cn.interestingshop.service.notice;

import java.util.List;

import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;

public interface NoticeService{
	/**
	 * 保存新闻
	 * @param notice
	 */
	void save(Notice notice);//保存新闻
	/**
	 * 根据id查询新闻
	 * @param parameter
	 * @return
	 */
	Notice getById(String parameter);
	/***
	 * 删除新闻
	 * @param id
	 */
	void deleteById(String id);
	/***
	 * 查询新闻列表
	 * @param param
	 * @return
	 */
	List<Notice> getList(NoticeParams param);
	/***
	 * 查询数目
	 * @param param
	 * @return
	 */
	Integer getCount(NoticeParams param);

}
