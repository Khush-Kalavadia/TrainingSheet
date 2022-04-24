package commonutil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor
{
    public String intercept(ActionInvocation invocation)
    {
        String result = "error";

        Map<String, Object> session = invocation.getInvocationContext().getSession();

        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

        try
        {
            if ((session != null && session.get("user") != null && session.get("user").equals("admin")) || (request.getRequestURI().equals("/login")))      //admin fixed if i keep list then anyone of them can login.
            {
                result = invocation.invoke();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
}
