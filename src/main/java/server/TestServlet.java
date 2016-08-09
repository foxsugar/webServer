package server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by SunXianping on 2016/7/28 0028.
 */
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        super.doGet(req,resp);
        Enumeration<String> headers = req.getHeaderNames();
        System.out.println("==============================");


        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");





//        resp.setHeader();
        resp.setHeader("Cache-Control", "max-age= 60");

//        resp.setHeader("Cache-Control", "max-age=private");



        PrintWriter out = resp.getWriter();
        out.println("<html>");

        out.println("Servlet名称为："+getServletConfig().getServletName() +"<br>");
// getServletConfig().getServletName()从配置文件中获取改servlet对应的name即ConfigTest
        Enumeration e= getServletConfig().getInitParameterNames();
//获取参数
        out.println("下面是Servlet设置的初始化参数："+"<br>");
        while(e.hasMoreElements())
        {
            String Key = (String)e.nextElement();
            String value = this.getInitParameter(Key);
            out.println("&nbsp;&nbsp;"+ Key + "="+value + "<br");
        }
        ServletContext context = getServletContext();
        String path = context.getRealPath("/");
        out.println("1当前路径信息的本地目录为"+path +"<br>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return System.currentTimeMillis();
    }


}
