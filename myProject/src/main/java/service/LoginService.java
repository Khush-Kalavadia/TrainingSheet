package service;

import bean.LoginBean;

public class LoginService
{
    private LoginBean bean;

    public LoginService(LoginBean bean)
    {
        this.bean = bean;
    }

    public boolean validate()                   //todo validate the user based on data coming from dao
    {
        boolean loginStatus = false;

        try
        {
            if (bean.getUsername().equals("admin") & bean.getPassword().equals("admin"))
            {
                bean.setMessage("User is valid");

                loginStatus = true;
            }
            else
            {
                bean.setMessage("user is not valid");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return loginStatus;
    }
}
