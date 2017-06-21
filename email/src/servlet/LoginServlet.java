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
		String type=req.getParameter("type");           //���ղ�������
		String username = req.getParameter("username"); //�����û���
		String password = req.getParameter("password"); //��������
		UserDao ud = new UserDao();
		String error = null;
		/*�˳���¼*/
		if(type.equals("quit")){               //�ж�Ҫ���еĲ���
			req.getSession().invalidate();     //����sessionʧЧ                     
			resp.sendRedirect("index.jsp");    //������ҳ
		/*��¼�ж�*/
		}else{
			/*�˺Ż�����Ϊ�շ�����Ӧ��ʾ*/
			if ((username == null || username == "")
					&& (password == null || password == "")) {
				error = "�˺����벻��Ϊ�գ�";
				req.getSession().setAttribute("error1", error);
				resp.sendRedirect("index.jsp");
			} else {
				boolean is = ud.login(username, password); //����login()������ѯ���ݿ⣬�Ƿ���ڴ��û�
				/*����������ҳ��*/
				if (is) {
					req.getSession().setAttribute("name", username);
					resp.sendRedirect("receiveList");
				/*���������ص�¼ҳ�棬��������Ӧ��ʾ*/
				} else {
					error = "�˺Ż����벻��ȷ��";
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
