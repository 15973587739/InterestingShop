package cn.interestingshop.web.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;

import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.service.order.CartService;
import cn.interestingshop.service.order.CartServiceImpl;
import cn.interestingshop.service.order.OrderService;
import cn.interestingshop.service.order.OrderServiceImpl;
import cn.interestingshop.utils.AlipayUtil;
import cn.interestingshop.utils.ShopCart;
import cn.interestingshop.web.AbstractServlet;

/**
 * 支付处理控制器
 *
 * @author InterestingShop.team
 *
 */
@WebServlet(urlPatterns = {"/Alipay"}, name = "Alipay")
public class AliPaymentServlet extends AbstractServlet {

	public static final Logger logger = Logger.getLogger(AliPaymentServlet.class);
	
    private OrderService orderService;
    private CartService cartService;
    private GoodsService goodsService;

    public void init() throws ServletException {
        orderService = new OrderServiceImpl();
        cartService = new CartServiceImpl();
        goodsService = new GoodsServiceImpl();
    }
    
	@Override
	public Class getServletClass() {
		return AliPaymentServlet.class;
	}
    
	   /**
     * 从session中获取购物车
     *
     * @param request
     * @return
     */
    private ShopCart getCartFromSession(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ShopCart cart = (ShopCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShopCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
	
	/**
	 * 向支付宝提交支付请求
	 *
	 * @param WIDout_trade_no
	 *            商户订单号，商户网站订单系统中唯一订单号，必填
	 * @param WIDsubject
	 *            订单名称，必填
	 * @param WIDtotal_amount
	 *            付款金额，必填
	 */
	public void pay(HttpServletRequest request, HttpServletResponse response)  throws Exception{
			
			//获取购物车中的内容
		 	ShopCart cart = getCartFromSession(request);
	        cart = cartService.calculate(cart);
			
			String WIDout_trade_no = request.getParameter("orderNo");
			String WIDsubject = request.getParameter("orderSize");
			String WIDtotal_amount = request.getParameter("amount");
			// 超时时间 可空
			String timeout_express = "2m";
			// 销售产品码 必填
//			String goods_code = "QUICK_WAP_PAY";
			String goods_code = "FAST_INSTANT_TRADE_PAY";
			/**********************/
			// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
			// 调用RSA签名方式
			AlipayClient client = new DefaultAlipayClient(AlipayUtil.url,
					AlipayUtil.appID, AlipayUtil.rsaPrivateKey,
					AlipayUtil.format, AlipayUtil.charset,
					AlipayUtil.alipayPublicKey, AlipayUtil.signType);
			AlipayTradePagePayRequest  alipay_request = new AlipayTradePagePayRequest ();

			// 封装请求支付信息
			AlipayTradePagePayModel model = new AlipayTradePagePayModel();
			model.setOutTradeNo(WIDout_trade_no);
			model.setSubject(WIDsubject);
			model.setTotalAmount(WIDtotal_amount);
			model.setTimeoutExpress(timeout_express);
			model.setProductCode(goods_code);
			alipay_request.setBizModel(model);
			// 设置异步通知地址
			alipay_request.setNotifyUrl(AlipayUtil.notifyUrl);
			// 设置同步地址
			alipay_request.setReturnUrl(AlipayUtil.returnUrl);
			// form表单生产
			String form = "";
			// 调用SDK生成表单
			form = client.pageExecute(alipay_request).getBody();
			System.out.println(form);
			response.setContentType("text/html;charset=" + AlipayUtil.charset);
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
	}

}
