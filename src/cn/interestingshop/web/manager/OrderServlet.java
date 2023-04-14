package cn.interestingshop.web.manager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.entity.BaseOrder;
import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.service.order.OrderService;
import cn.interestingshop.service.order.OrderServiceImpl;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.Pager;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/5/6.
 */
@WebServlet(urlPatterns = {"/manager/order"},name = "order")
public class OrderServlet extends AbstractServlet{

    private OrderService orderService;

    public void init() throws ServletException {
        orderService = new OrderServiceImpl();
    }
    /**
     * 订单的主页面
     * @param request
     * @param response
     * @return
     */
    public String index(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取用户id
        String userId=request.getParameter("userId");
        //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?5:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        int total=orderService.count(Integer.valueOf(userId));
        Pager pager=new Pager(total,rowPerPage,currentPage);
        pager.setUrl("/manager/order?action=index&userId="+userId);
        List<BaseOrder> orderList=orderService.getBaseList(Integer.valueOf(userId), currentPage, rowPerPage);
        request.setAttribute("orderList", orderList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 1);
        return "/manager/order/orderList";
    }
    /**
     * 查询订单明细
     * @param request
     * @param response
     * @return
     */
    public String queryOrderInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String baseOrderId=request.getParameter("baseOrderId");
        List<OrderInfo> orderInfoList=orderService.getOrderInfoList(Integer.valueOf(baseOrderId));
        request.setAttribute("orderInfoList", orderInfoList);
        request.setAttribute("menu", 1);
        return "/manager/order/orderInfoList";
    }
    
    public String queryAllOrder(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取当前页数
        String currentPageStr = request.getParameter("currentPage");
        //获取页大小
        String pageSize = request.getParameter("pageSize");
        int rowPerPage  = EmptyUtils.isEmpty(pageSize)?5:Integer.parseInt(pageSize);
        int currentPage = EmptyUtils.isEmpty(currentPageStr)?1:Integer.parseInt(currentPageStr);
        int total=orderService.count(null);
        Pager pager=new Pager(total,rowPerPage,currentPage);
        pager.setUrl("/manager/order?action=queryAllOrder");
        List<BaseOrder> orderList=orderService.getBaseList(null, currentPage, rowPerPage);
        request.setAttribute("orderList", orderList);
        request.setAttribute("pager", pager);
        request.setAttribute("menu", 9);
        return "/manager/order/orderList";
    }

    @Override
    public Class getServletClass() {
        return OrderServlet.class;
    }
}
