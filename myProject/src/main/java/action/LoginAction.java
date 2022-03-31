package action;

import com.opensymphony.xwork2.ModelDriven;
import bean.LoginBean;
import service.LoginService;

public class LoginAction implements ModelDriven<LoginBean>
{
    LoginBean bean = new LoginBean();

    LoginService loginService;

    boolean loginStatus;

    public String loginCheck()
    {
        loginService = new LoginService(bean);

        loginStatus = loginService.validate();          //fixme havent used loginStatus else turn to void

        return "success";
    }

    @Override
    public LoginBean getModel()
    {
        return bean;
    }
}

