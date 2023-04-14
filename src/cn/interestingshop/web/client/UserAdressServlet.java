package cn.interestingshop.web.client;

import javax.servlet.ServletException;

import cn.interestingshop.service.user.UserAddressService;
import cn.interestingshop.service.user.UserAddressServiceImpl;
import cn.interestingshop.web.AbstractServlet;

/**
 * Created by bdqn on 2016/5/12.
 */
public class UserAdressServlet extends AbstractServlet {

    private UserAddressService userAddressService;

    public void init() throws ServletException {
        userAddressService = new UserAddressServiceImpl();
    }

    @Override
    public Class getServletClass() {
        return UserAdressServlet.class;
    }
}
