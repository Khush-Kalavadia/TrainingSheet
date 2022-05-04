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
            if ((session != null && session.get("user") != null && session.get("user").equals("admin")) || (request.getRequestURI().equals("/login")))      //admin fixed if i keep list then anyone of them can login. let say there are 2 user admin and adminNew. If i log out from admin and adminNew is logged in. Now if i keep the condition of user being not null then adminNew would be there and would also allow admin to invoke the request. For that i will have to get user sending me this request everytime
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
