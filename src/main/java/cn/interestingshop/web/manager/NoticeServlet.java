package cn.interestingshop.web.manager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.service.notice.NoticeService;
import cn.interestingshop.service.notice.NoticeServiceImpl;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.web.AbstractServlet;

@WebServlet(urlPatterns = {"/manager/notice"},name = "managerNotice")
public class NoticeServlet extends AbstractServlet {

	private NoticeService noticeService;

	public void init() throws ServletException {
		this.noticeService = new NoticeServiceImpl();
	}

	@Override
	public Class getServletClass() {
		return NoticeServlet.class;
	}

	/**
	 * 查询新闻列表
	 * @param request
	 * @param response
	 * @return
	 */
	public String queryNoticeList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//获取当前页数
		String currentPageStr = request.getParameter("currentPage");
		//获取页大小
		String pageSize = request.getParameter("pageSize");
		int rowPerPage = EmptyUtils.isEmpty(pageSize)?10:Integer.parseInt(pageSize);
		int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
		int total= noticeService.getCount(new NoticeParams());
		Pager pager=new Pager(total,rowPerPage,currentPage);
		pager.setUrl("/manager/notice?action=queryNoticeList");
		NoticeParams params = new NoticeParams();
        params.openPage((pager.getCurrentPage() - 1) * pager.getRowPerPage(),pager.getRowPerPage());
		List<Notice> noticeList = noticeService.getList(params);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 7);
		return "/manager/notice/noticeList";
	}
	/**
	 * 查询新闻详情
	 * @param request
	 * @param response
	 * @return
	 */
	public String noticeDetail(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String id = request.getParameter("id");
		Notice Notice = noticeService.getById(Integer.valueOf(id));
		request.setAttribute("Notice",Notice);
		request.setAttribute("menu", 7);
		return "/manager/notice/noticeDetail";
	}

}
