package action;

import com.opensymphony.xwork2.ModelDriven;
import bean.LoginBean;
import service.LoginService;

public class LoginAction implements ModelDriven<LoginBean>
{
    LoginBean loginBean = new LoginBean();

    LoginService loginService = new LoginService();

    public String loginCheck()
    {
        loginBean.setLogin(loginService.validate(loginBean.getUsername(), loginBean.getPassword()));          //fixme havent used the boolean returned from loginStatus else turn to void

        return "success";
    }

    @Override
    public LoginBean getModel()
    {
        return loginBean;
    }
}

