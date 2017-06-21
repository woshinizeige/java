package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class DeleteAllServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String id=req.getParameter("id");            //要删除的邮件ID
		int id1=Integer.parseInt(id);
		String type=req.getParameter("type");       //根据type的不同进行不同的操作
		UserDao ud=new UserDao();
		if(type.equals("r")){
			ud.delete(id1);
		}else if(type.equals("s")){
			ud.deleteS(id1);
		}
		resp.getWriter();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
