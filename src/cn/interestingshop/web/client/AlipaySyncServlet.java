package cn.interestingshop.web.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import cn.interestingshop.entity.OrderInfo;
import cn.interestingshop.service.goods.GoodsService;
import cn.interestingshop.service.goods.GoodsServiceImpl;
import cn.interestingshop.service.order.OrderService;
import cn.interestingshop.service.order.OrderServiceImpl;
import cn.interestingshop.utils.AlipayUtil;

@WebServlet(urlPatterns = { "/trackPaymentStatus" }, name = "trackPaymentStatus")
public class AlipaySyncServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(AlipaySyncServlet.class);
	private OrderService orderService;
	private GoodsService goodsService;
	
	public AlipaySyncServlet() {
		super();
	}

	public void destroy() {
		this.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes(
					"ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status")
					.getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
			// publicKey, String charset, String sign_type)
			boolean verify_result = AlipaySignature.rsaCheckV1(params,
					AlipayUtil.alipayPublicKey, AlipayUtil.charset, "RSA2");

			if (verify_result) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码		

				//即时到账普通版，那么这时的交易状态值为：  TRADE_FINISHED；如果是即时到账高级版，此时的交易状态值就为：TRADE_SUCCESS	
				//收到TRADE_FINISHED请求后，这笔订单就结束了，支付宝不会再主动请求商户网站了；收到TRADE_SUCCESS请求后，后续一定还有至少一条通知记录，即TRADE_FINISHED。
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//修改库存
					System.out.println("减库存！！！！！！！！！！！！！！！！！！！");
					List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
//					orderInfos = orderService.getOrderInfoByOrderorderNo(out_trade_no);
					for(OrderInfo orderInfo:orderInfos){
						goodsService.updateStock(orderInfo.getGoodsId(), orderInfo.getBuyNum());
					}
					// 注意：
					// 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
					// 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					// 如果有做过处理，不执行商户的业务程序
					System.out.println("减库存！！！！！！！！！！！！！！！！！！！");
					List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
//					orderInfos = orderService.getOrderInfoByOrderorderNo(out_trade_no);
					for(OrderInfo orderInfo:orderInfos){
						goodsService.updateStock(orderInfo.getGoodsId(), orderInfo.getBuyNum());
					}
					// 注意：
					// 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
				}
				
				response.getWriter().println("success"); // 请不要修改或删除

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
//				orderService.payFailed(out_trade_no, 1,trade_no);
//				response.getWriter().println("fail");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());			
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void init() throws ServletException {
		orderService = new OrderServiceImpl();
		goodsService = new GoodsServiceImpl();
	}

}
