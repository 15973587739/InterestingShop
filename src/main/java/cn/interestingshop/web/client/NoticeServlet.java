package cn.interestingshop.web.client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.service.notice.NoticeService;
import cn.interestingshop.service.notice.NoticeServiceImpl;
import cn.interestingshop.web.AbstractServlet;
/**
 * Created by bdqn on 2016/4/25.
 */
@WebServlet(urlPatterns = {"/notice"}, name = "notice")
public class NoticeServlet extends AbstractServlet {

    private NoticeService noticeService;

    public void init() throws ServletException {
        noticeService = new NoticeServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return NoticeServlet.class;
    }

    public String index(HttpServletRequest request, HttpServletResponse response)throws Exception{
        return "/client/noticeList";
    }

}
