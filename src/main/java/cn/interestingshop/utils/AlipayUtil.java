package cn.interestingshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AlipayUtil {
	
	public AlipayUtil alipayUtil = null;
	/**
	 * 商户appid
	 */
	public static String appID;
	/**
	 * 私钥 pkcs8格式的
	 */
	public static String rsaPrivateKey;
	/**
	 * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	 */
	public static String notifyUrl;
	/**
	 * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	 */
	public static String returnUrl;
	/**
	 * 请求网关地址
	 */
	public static String url;
	/**
	 * 编码
	 */
	public static String charset;
	/**
	 * 返回格式
	 */
	public static String format;
	/**
	 * 支付宝公钥
	 */
	public static String alipayPublicKey;
	/**
	 * 日志记录目录
	 */
	public static String logPath;
	/**
	 * RSA2
	 */
	public static String signType;
	
	/**
	 * 支付成功跳转页面
	 */
	public static String paymentSuccessUrl;
	/**
	 * 支付失败跳转页面
	 */
	public static String paymentFailureUrl;
	
	static{
		init();
	}

	public static void init(){
		Properties params=new Properties();
		String configFile = "thirdpart.properties";
		InputStream is=DataSourceUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		appID=params.getProperty("appID");
		rsaPrivateKey=params.getProperty("rsaPrivateKey");
		notifyUrl=params.getProperty("notifyUrl");
		returnUrl=params.getProperty("returnUrl");
		url=params.getProperty("url");
		charset=params.getProperty("charset");
		format=params.getProperty("format");
		alipayPublicKey=params.getProperty("alipayPublicKey");
		logPath=params.getProperty("logPath");
		signType=params.getProperty("signType");
		paymentSuccessUrl=params.getProperty("paymentSuccessUrl");
		paymentFailureUrl=params.getProperty("paymentFailureUrl");
	}
}
