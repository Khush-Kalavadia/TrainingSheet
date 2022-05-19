package action;

import com.opensymphony.xwork2.ModelDriven;
import bean.LoginBean;
import org.apache.struts2.interceptor.SessionAware;
import service.LoginService;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LoginAction implements ModelDriven<LoginBean>, SessionAware
{
    private LoginBean loginBean = new LoginBean();

    private Map<String, Object> session = new HashMap<>();

    public String loginCheck()
    {
        try
        {
            LoginService.validate(loginBean);

            if (loginBean.isLogin())
            {
                session.put("user", loginBean.getUsername());

                if (session.get("page") == null)
                {
                    session.put("page", "dashboard");
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "success";
    }

    public String logout()
    {
        try
        {
            LoginService.logout(loginBean);

            session.remove("user");
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

    @Override
    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }
}