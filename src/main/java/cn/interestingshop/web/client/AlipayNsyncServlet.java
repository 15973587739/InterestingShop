package cn.interestingshop.web.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import cn.interestingshop.utils.AlipayUtil;

@WebServlet(urlPatterns = { "/callback" }, name = "callback")
public class AlipayNsyncServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AlipayNsyncServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		this.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			String out_trade_no = new String(request.getParameter("out_trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes(
					"ISO-8859-1"), "UTF-8");
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayUtil.alipayPublicKey, AlipayUtil.charset,
					"RSA2"); // 调用SDK验证签名

			// ——请在这里编写您的程序（以下代码仅作参考）——
			if (signVerified) {
				//跳转到支付成功的页面
				response.sendRedirect(
						String.format(AlipayUtil.paymentSuccessUrl, out_trade_no));
			} else {
				//跳转到支付失败的页面
				response.sendRedirect(
						String.format(AlipayUtil.paymentFailureUrl, out_trade_no));
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
	}

}
