package cn.interestingshop.web.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.interestingshop.entity.User;
import cn.interestingshop.service.user.UserService;
import cn.interestingshop.service.user.UserServiceImpl;
import cn.interestingshop.utils.Constants;
import cn.interestingshop.utils.EmptyUtils;
import cn.interestingshop.utils.RegUtils;
import cn.interestingshop.utils.ReturnResult;
import cn.interestingshop.utils.SecurityUtils;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/5/3.
 */
@WebServlet(urlPatterns = {"/register"}, name = "register")
public class RegisterServlet extends AbstractServlet {

    private UserService userService;

    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    /**
     * 返回
     *
     * @return
     */
    @Override
    public Class getServletClass() {
        return RegisterServlet.class;
    }

    /**
     * 跳到注册页面
     *
     * @param request
     * @param response
     * @return
     */
    public String toRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/client/register";
    }

    /**
     * 保存用户信息
     *
     * @param request
     * @param response
     * @return
     */
    public ReturnResult saveUserToDatabase(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnResult result = new ReturnResult();
        try {
            User user = new User();
            String account = request.getParameter("account");
            String gender = request.getParameter("gender");
            User oldUser = userService.getById(null, account);
            //判断用户
            if (EmptyUtils.isNotEmpty(oldUser)) {
                result.returnFail("用户已经存在");
                return result;
            }
            user.setAccount(request.getParameter("account"));
            user.setNickName(request.getParameter("nickName"));
            user.setGender(EmptyUtils.isEmpty(gender) ? 1 : 0);
            user.setPassword(SecurityUtils.md5Hex(request.getParameter("password")));
            user.setIdCardNo(request.getParameter("idCardNo"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phone"));
            user.setType(Constants.UserType.CLIENT);
            result=checkUser(user);
            //是否验证通过
            if(result.getStatus()==Constants.ReturnResult.SUCCESS){
            	 if(!userService.save(user)){
                	 return result.returnFail("注册失败！");
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.returnSuccess("注册成功");
        return result;
    }
    
    private ReturnResult checkUser(User user){
    	ReturnResult result = new ReturnResult();
    	if(EmptyUtils.isNotEmpty(user.getPhone())){
    		if(!RegUtils.checkphone(user.getPhone())){
    			return result.returnFail("手机格式不正确");
    		}
    	}
    	
    	if(EmptyUtils.isNotEmpty(user.getIdCardNo())){
    		if(!RegUtils.checkidCardNoReg(user.getIdCardNo())){
    			return result.returnFail("身份证号码不正确");
    		}
    	}
    	
    	if(EmptyUtils.isNotEmpty(user.getEmail())){
    		if(!RegUtils.checkEmail(user.getEmail())){
    			return result.returnFail("邮箱格式不正确");
    		}
    	}
    	return result.returnSuccess();
    }
}
