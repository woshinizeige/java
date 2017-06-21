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
		String id=req.getParameter("id");            //Ҫ�鿴���ʼ�ID
		String type=req.getParameter("type");        //�鿴���͵Ļ��߽��յ�
		int id1=Integer.parseInt(id);
		UserDao ud=new UserDao();
		Receive r=new Receive();
		Send s=new Send();
		ud.update(id1);                                //����update()�������ʼ�״̬�ı�Ϊ�Ѷ�
		if(type.equals("r")){
			r=ud.emailR(id1);                          //ͨ��ID��ѯ�����ʼ�
			req.getSession().setAttribute("email", r);
			resp.sendRedirect("readMsg.jsp");
		}else if(type.equals("s")){
			s=ud.emailS(id1);                          //ͨ��ID��ѯ�ѷ��ʼ�
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
