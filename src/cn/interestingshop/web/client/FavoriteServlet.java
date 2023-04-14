package cn.interestingshop.web.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.interestingshop.entity.Goods;
import cn.interestingshop.entity.User;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.MemcachedUtils;
import cn.interestingshop.utils.ReturnResult;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/5/5.
 */
@WebServlet(urlPatterns = {"/favorite"}, name = "favorite")
public class FavoriteServlet extends AbstractServlet {


    private GoodsService goodsService;

    public void init() throws ServletException {
        goodsService = new GoodsServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return FavoriteServlet.class;
    }

    /**
     * 跳转到历史记录
     *
     * @param request
     * @param response
     * @return
     */
    public String toFavoriteList(HttpServletRequest request, HttpServletResponse response)throws Exception {
        List<Goods> recentGoods=queryFavoriteList(request);
        request.setAttribute("recentGoods",recentGoods);
        return "/client/goods/favoriteList";
    }

    /**
     * 添加到收藏
     *
     * @return
     */
    public ReturnResult addFavorite(HttpServletRequest request, HttpServletResponse response)throws Exception {
        ReturnResult result = new ReturnResult();
        String id = request.getParameter("id");
        Goods goods = goodsService.getById(Integer.valueOf(id));
        List<Goods> favoriteList = queryFavoriteList(request);
        //判断是否满了
        if (favoriteList.size() > 0 && favoriteList.size() == 5) {
            favoriteList.remove(0);
        }
        boolean temp = false;
        for (int i = 0; i < favoriteList.size(); i++) {
            if (favoriteList.get(i).getId().equals(goods.getId())) {
                temp = true;
                break;
            }
        }
        if (!temp) {
            favoriteList.add(favoriteList.size(), goods);
        }
        MemcachedUtils.add(getFavoriteKey(request),favoriteList);
        result.returnSuccess();
        return result;
    }

    /**
     * 查询最近商品
     *
     * @return
     */
    private List<Goods> queryFavoriteList(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        //判断用户是否登录
        String key = EmptyUtils.isEmpty(user) ? session.getId() : user.getAccount();
        List<Goods> recentGoods = (List<Goods>) MemcachedUtils.get(key);
        if (EmptyUtils.isEmpty(recentGoods)) {
            recentGoods = new ArrayList<Goods>();
        }
        return recentGoods;
    }
    /**
     *
     * @param request
     * @return
     */
    private String getFavoriteKey(HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        return EmptyUtils.isEmpty(user) ? session.getId() : user.getAccount();
    }
}
