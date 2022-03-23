package example;

import com.opensymphony.xwork2.ModelDriven;

public class Example implements ModelDriven<ExampleBean>
{
    ExampleBean bean = new ExampleBean();

    public String login()
    {
        if (bean.getUsername().equals("admin"))
        {
            System.out.println(bean.getUsername());

            bean.setMessage("User is valid");

            return "success";
        }
        else
        {
            bean.setMessage("user is not valid");

            return "error";
        }
    }

    @Override
    public ExampleBean getModel()
    {
        return bean;
    }
}

