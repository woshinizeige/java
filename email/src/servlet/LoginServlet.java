package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String type=req.getParameter("type");           //接收操作类型
		String username = req.getParameter("username"); //接收用户名
		String password = req.getParameter("password"); //接收密码
		UserDao ud = new UserDao();
		String error = null;
		/*退出登录*/
		if(type.equals("quit")){               //判定要进行的操作
			req.getSession().invalidate();     //设置session失效                     
			resp.sendRedirect("index.jsp");    //返回首页
		/*登录判定*/
		}else{
			/*账号或密码为空返回相应提示*/
			if ((username == null || username == "")
					&& (password == null || password == "")) {
				error = "账号密码不能为空！";
				req.getSession().setAttribute("error1", error);
				resp.sendRedirect("index.jsp");
			} else {
				boolean is = ud.login(username, password); //调用login()方法查询数据库，是否存在此用户
				/*存在跳到主页面*/
				if (is) {
					req.getSession().setAttribute("name", username);
					resp.sendRedirect("receiveList");
				/*不存在跳回登录页面，并返回相应提示*/
				} else {
					error = "账号或密码不正确！";
					req.getSession().setAttribute("error1", error);
					resp.sendRedirect("index.jsp");
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
