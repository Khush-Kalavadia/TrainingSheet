package action;

import com.opensymphony.xwork2.ModelDriven;
import bean.LoginBean;
import service.LoginService;

public class LoginAction implements ModelDriven<LoginBean>
{
    private LoginBean loginBean = new LoginBean();

    private LoginService loginService = new LoginService();

    public String loginCheck()
    {
        try
        {
            loginBean.setLogin(loginService.validate(loginBean.getUsername(), loginBean.getPassword()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return "success";
    }

    @Override
    public LoginBean getModel()
    {
        return loginBean;
    }
}