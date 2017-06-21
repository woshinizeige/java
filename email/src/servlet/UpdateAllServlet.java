package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;


public class UpdateAllServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String id=req.getParameter("id");            //Ҫ�鿴���ʼ�ID
		int id1=Integer.parseInt(id);
		UserDao ud=new UserDao();
		ud.update(id1);                                //����update()�������ʼ�״̬�ı�Ϊ�Ѷ�
		resp.getWriter();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
