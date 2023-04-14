package cn.interestingshop.web.client;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.entity.Goods;
import cn.interestingshop.entity.Notice;
import cn.interestingshop.param.NoticeParams;
import cn.interestingshop.service.goods.ClassifyService;
import cn.interestingshop.service.goods.ClassifyServiceImpl;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.service.notice.NoticeService;
import cn.interestingshop.service.notice.NoticeServiceImpl;
import cn.interestingshop.utils.ClassifyVo;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.web.AbstractServlet;

@WebServlet(urlPatterns = {"/home"}, name = "home")
public class HomeServlet extends AbstractServlet {

    private GoodsService goodsService;
    private NoticeService noticeService;
    private ClassifyService classifyService;

    public void init() throws ServletException {
        goodsService = new GoodsServiceImpl();
        noticeService = new NoticeServiceImpl();
        classifyService = new ClassifyServiceImpl();
    }

    /**
     * 商城主页的方法
     * @param request
     * @param response
     * @return
     */
    public String index(HttpServletRequest request, HttpServletResponse response)throws Exception {
        //查询商品分裂
        List<ClassifyVo> classifyVoList = classifyService.getList();
        Pager pager= new Pager(5, 5, 1);
        NoticeParams params = new NoticeParams();
        params.openPage((pager.getCurrentPage() - 1) * pager.getRowPerPage(),pager.getRowPerPage());
        List<Notice> Notice = noticeService.getList(params);
        //查询一楼
        for (ClassifyVo vo : classifyVoList) {
            List<Goods> goodsList = goodsService.getList(1, 8, null, vo.getClassify().getId());
            vo.setGoodsList(goodsList);
        }
        //封装返回
        request.setAttribute("classifyVoList", classifyVoList);
        request.setAttribute("notice", Notice);
        return "/client/index";
    }

    @Override
    public Class getServletClass() {
        return HomeServlet.class;
    }
}
