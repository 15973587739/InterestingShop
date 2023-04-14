package cn.interestingshop.web.client;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/4/21.
 */
@WebServlet(urlPatterns = {"/classify"},name = "classify")
public class ClassifyServlet extends AbstractServlet{

    private GoodsService goodsService;

    public void init() throws ServletException {
        goodsService = new GoodsServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return ClassifyServlet.class;
    }
}
