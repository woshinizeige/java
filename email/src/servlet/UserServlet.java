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
		String toname=req.getParameter("toname");                 //�ʼ��ķ�����
		String fromname=req.getParameter("fromname");             //�ʼ��Ľ�����
		UserDao ud=new UserDao();
		List<Users> list=ud.toname(toname);                       //�������б�
		req.getSession().setAttribute("toname", fromname);        //Ĭ�Ͻ�����
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
