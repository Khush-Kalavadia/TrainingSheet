package service;

import bean.LoginBean;
import helper.LoginTableHelper;

import java.util.HashMap;

public class LoginService
{
    public static void validate(LoginBean loginBean)
    {
        HashMap<String, Object> resultSetRow;

        try
        {
            resultSetRow = LoginTableHelper.getRowByUsernamePassword(loginBean.getUsername(), loginBean.getPassword());

            if (resultSetRow != null && !resultSetRow.isEmpty())
            {
                loginBean.setLogin(true);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void logout(LoginBean loginBean)
    {
        try
        {
            loginBean.setUsername(null);

            loginBean.setPassword(null);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
