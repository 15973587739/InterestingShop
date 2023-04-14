package cn.interestingshop.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式验证 工具类
 * @author InterestingShop.team
 *
 */
public class RegUtils {

	static String emailReg="^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
	static String phoneReg="^\\d{5,11}$";
	static String idCardNoReg="^\\w{18}$";
	
	public static boolean checkEmail(String email){
		Pattern pattern=Pattern.compile(emailReg);
		Matcher matcher=pattern.matcher(email);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
	public static boolean checkphone(String phone){
		Pattern pattern=Pattern.compile(phoneReg);
		Matcher matcher=pattern.matcher(phone);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
	public static boolean checkidCardNoReg(String idCardNo){
		Pattern pattern=Pattern.compile(idCardNoReg);
		Matcher matcher=pattern.matcher(idCardNo);
		System.out.println(matcher.matches());
		return matcher.matches();
	}
	
}
