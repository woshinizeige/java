package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class RegsiterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String type = req.getParameter("type"); // �õ�Ҫ���еĲ���
		String username = req.getParameter("username"); // �õ����û�
		String password = req.getParameter("password"); // �õ�����
		String affirm = req.getParameter("affirm"); // �õ��ڶ������������
		String email = req.getParameter("email"); // �õ������ַ
		PrintWriter pw=resp.getWriter();
		UserDao us = new UserDao();
		String error = null;
		int result = 0;
		String zc=null;
		/* ���û��������ж� */
		if (type.equals("nam")) {
			/* �û�Ϊ�� */
			if (username == null || username == "") {
				error = "�û�������Ϊ�գ�";
			} else {
				boolean is = us.selectname(username);
				/* �û��Ѵ��� */
				if (is) {
					error = "�û��Ѵ��ڣ�����ʹ�ã�";
				} else {
					error = "�û�����ʹ�ã�";
				}
			}
		} else
		/* ����������ж� */
		if (type.equals("pas")) {
			/* ����Ϊ�� */
			if (password == null || password == "") {
				error = "���벻��Ϊ�գ�";
				/* ����С��6 */
			} else if (password.length() < 6) {
				error = "���볤�Ȳ���С��6��";
			}else{
				error="�������ʹ��";
			}
		} else
		/* �Եڶ�����������ж� */
		if (type.equals("aff")) {
			/* ����Ϊ�� */
			if (affirm == null || affirm == "") {
				error = "���벻��Ϊ�գ�";
			} else
			/* ����С��6 */
			if (affirm.length() < 6) {
				error = "���볤�Ȳ���С��6��";
			} else
			/* �������벻һ�� */
			if (!password.equals(affirm)) {
				error = "�������벻һ�£�";
			}else{
				error="��������һ��";
			}
		} else
		/* �ж����� */
		if (type.equals("ema")) {
			/* ����Ϊ�� */
			if (email == null || email == "") {
				error = "���䲻��Ϊ�գ�";
			}else{
				error="���ٴ�ȷ��ע����Ϣ��";
			}
		}else{
			/* �û�Ϊ�� */
			if (username == null || username == "") {
				zc="yhk";
				req.getSession().setAttribute("zc", zc);
			} else 
				/* �û��Ѵ��� */
			if (us.selectname(username)) {
				zc="yhc";
				req.getSession().setAttribute("zc", zc);
				
			}else
			/* ����Ϊ�� */
			if (password == null || password == ""||affirm == null || affirm == "") {
				zc="mmk";
				req.getSession().setAttribute("zc", zc);
			} else
			/* ����С��6 */
			if (password.length() < 6||affirm.length() < 6) {
				zc="mmd";
				req.getSession().setAttribute("zc", zc);
			} else
			/* �������벻һ�� */
			if (!password.equals(affirm)) {
				zc="mmb";
				req.getSession().setAttribute("zc", zc);
			}else
				/* ����Ϊ�� */
			if (email == null || email == "") {
				zc="yxk";
				req.getSession().setAttribute("zc", zc);
			}else{
			/*û�д��󣬽���ע��*/
			result = us.register(username, password, email);
			if (result > 0) {
				zc="cg";
				req.getSession().setAttribute("zc", zc);
			} else {
				zc="sb";
				req.getSession().setAttribute("zc", zc);
			}
			}
			resp.sendRedirect("register.jsp");
		}
		pw.print(error);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
