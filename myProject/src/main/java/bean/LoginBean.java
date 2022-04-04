package bean;

public class LoginBean
{
    private String username;

    private String password;

    private boolean login;

    public String getUsername()
    {

        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isLogin()
    {
        return login;
    }

    public void setLogin(boolean login)
    {
        this.login = login;
    }
}
