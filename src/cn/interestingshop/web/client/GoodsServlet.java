package cn.interestingshop.web.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.interestingshop.entity.Goods;
import cn.interestingshop.service.goods.ClassifyService;
import cn.interestingshop.service.goods.ClassifyServiceImpl;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.utils.ClassifyVo;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/4/26.
 */
@WebServlet(urlPatterns = {"/goods"}, name = "goods")
public class GoodsServlet extends AbstractServlet {

    private GoodsService goodsService;
    private ClassifyService classifyService;


    public void init() throws ServletException {
        goodsService = new GoodsServiceImpl();
        classifyService=new ClassifyServiceImpl();
    }

    /**
     * 查询商品列表
     *
     * @param request
     * @param response
     * @return
     */
    public String queryGoodsList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String category = request.getParameter("category");
        String levelStr = request.getParameter("level");
        String currentPageStr = request.getParameter("currentPage");
        String keyWord = request.getParameter("keyWord");
        //获取页大小
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = EmptyUtils.isEmpty(pageSizeStr) ? 20:Integer.parseInt(pageSizeStr);
        int pageIndex = EmptyUtils.isEmpty(currentPageStr) ? 1 : Integer.parseInt(currentPageStr);
        int  level=EmptyUtils.isNotEmpty(levelStr)?Integer.parseInt(levelStr):0;
        int total = goodsService.getCount(keyWord, EmptyUtils.isEmpty(category)?null:Integer.valueOf(category));
        Pager pager = new Pager(total, pageIndex, pageIndex);
        pager.setUrl("/goods?action=queryGoodsList&level="+level+"&category="+(EmptyUtils.isEmpty(category)?"":category));
        List<ClassifyVo> classifyVoList = classifyService.getList();
        
        pageIndex = (pageIndex-1)*pageSize;
        List<Goods> goodsList = goodsService.getList(pageIndex, pageSize, keyWord, EmptyUtils.isEmpty(category)?null:Integer.valueOf(category));
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("pager", pager);
        request.setAttribute("total", total);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("classifyVoList", classifyVoList);
        return "/client/goods/queryGoodsList";
    }
    /**
     *
     * @param request
     * @param response
     * @return
     */
    public String queryGoodsDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id = request.getParameter("id");
        Goods goods = goodsService.getById(Integer.valueOf(id));
        List<ClassifyVo> classifyVoList = classifyService.getList();
        request.setAttribute("goods", goods);
        request.setAttribute("classifyVoList", classifyVoList);
        addRecentgoods(request,goods);
        return "/client/goods/goodsDetail";
    }
    /**
     * 查询最近商品
     * @return
     */
    private List<Goods> queryrecentGoods(HttpServletRequest request)throws Exception{
        HttpSession session=request.getSession();
        List<Goods> recentGoods= (List<Goods>) session.getAttribute("recentGoods");
        if(EmptyUtils.isEmpty(recentGoods)){
            recentGoods=new ArrayList<Goods>();
        }
        return recentGoods;
    }
    /**
     * 添加最近浏览商品
     * @param request
     * @param goods
     */
    private void addRecentgoods(HttpServletRequest request,Goods goods)throws Exception{
        HttpSession session=request.getSession();
        List<Goods> recentGoods=queryrecentGoods(request);
        //判断是否满了
        if(recentGoods.size()>0 &&  recentGoods.size()==10){
          recentGoods.remove(0);
        }
        recentGoods.add(recentGoods.size(),goods);
        session.setAttribute("recentGoods",recentGoods);
    }

    @Override
    public Class getServletClass() {
        return GoodsServlet.class;
    }
}
