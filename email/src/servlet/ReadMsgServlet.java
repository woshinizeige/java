package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.Receive;
import entity.Send;

public class ReadMsgServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String id=req.getParameter("id");            //要查看的邮件ID
		String type=req.getParameter("type");        //查看发送的或者接收的
		int id1=Integer.parseInt(id);
		UserDao ud=new UserDao();
		Receive r=new Receive();
		Send s=new Send();
		ud.update(id1);                                //调用update()方法将邮件状态改变为已读
		if(type.equals("r")){
			r=ud.emailR(id1);                          //通过ID查询接收邮件
			req.getSession().setAttribute("email", r);
			resp.sendRedirect("readMsg.jsp");
		}else if(type.equals("s")){
			s=ud.emailS(id1);                          //通过ID查询已发邮件
			req.getSession().setAttribute("email", s);
			resp.sendRedirect("readMsg.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
