package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.Users;

public class UserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String toname=req.getParameter("toname");                 //邮件的发送人
		String fromname=req.getParameter("fromname");             //邮件的接收人
		UserDao ud=new UserDao();
		List<Users> list=ud.toname(toname);                       //接收人列表
		req.getSession().setAttribute("toname", fromname);        //默认接收人
		req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("str", null);
		resp.sendRedirect("newMsg.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
