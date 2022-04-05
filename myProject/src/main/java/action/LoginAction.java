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
        try
        {
            loginBean.setLogin(loginService.validate(loginBean.getUsername(), loginBean.getPassword()));          //fixme havent used the boolean returned from loginStatus else turn to void
//            Thread.sleep(3000);
//            System.out.println(10/0);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();           //in case of any error struts will handle the response
        }

        return "success";
    }

    @Override
    public LoginBean getModel()
    {
        return loginBean;
    }
}

