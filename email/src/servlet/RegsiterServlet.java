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
		String type = req.getParameter("type"); // 得到要进行的操作
		String username = req.getParameter("username"); // 得到新用户
		String password = req.getParameter("password"); // 得到密码
		String affirm = req.getParameter("affirm"); // 得到第二次输入的密码
		String email = req.getParameter("email"); // 得到邮箱地址
		PrintWriter pw=resp.getWriter();
		UserDao us = new UserDao();
		String error = null;
		int result = 0;
		String zc=null;
		/* 对用户名进行判定 */
		if (type.equals("nam")) {
			/* 用户为空 */
			if (username == null || username == "") {
				error = "用户名不能为空！";
			} else {
				boolean is = us.selectname(username);
				/* 用户已存在 */
				if (is) {
					error = "用户已存在，不可使用！";
				} else {
					error = "用户可以使用！";
				}
			}
		} else
		/* 对密码进行判定 */
		if (type.equals("pas")) {
			/* 密码为空 */
			if (password == null || password == "") {
				error = "密码不能为空！";
				/* 密码小于6 */
			} else if (password.length() < 6) {
				error = "密码长度不能小于6！";
			}else{
				error="密码可以使用";
			}
		} else
		/* 对第二次密码进行判定 */
		if (type.equals("aff")) {
			/* 密码为空 */
			if (affirm == null || affirm == "") {
				error = "密码不能为空！";
			} else
			/* 密码小于6 */
			if (affirm.length() < 6) {
				error = "密码长度不能小于6！";
			} else
			/* 两次密码不一致 */
			if (!password.equals(affirm)) {
				error = "两次密码不一致！";
			}else{
				error="两次密码一致";
			}
		} else
		/* 判定邮箱 */
		if (type.equals("ema")) {
			/* 邮箱为空 */
			if (email == null || email == "") {
				error = "邮箱不能为空！";
			}else{
				error="请再次确认注册信息。";
			}
		}else{
			/* 用户为空 */
			if (username == null || username == "") {
				zc="yhk";
				req.getSession().setAttribute("zc", zc);
			} else 
				/* 用户已存在 */
			if (us.selectname(username)) {
				zc="yhc";
				req.getSession().setAttribute("zc", zc);
				
			}else
			/* 密码为空 */
			if (password == null || password == ""||affirm == null || affirm == "") {
				zc="mmk";
				req.getSession().setAttribute("zc", zc);
			} else
			/* 密码小于6 */
			if (password.length() < 6||affirm.length() < 6) {
				zc="mmd";
				req.getSession().setAttribute("zc", zc);
			} else
			/* 两次密码不一致 */
			if (!password.equals(affirm)) {
				zc="mmb";
				req.getSession().setAttribute("zc", zc);
			}else
				/* 邮箱为空 */
			if (email == null || email == "") {
				zc="yxk";
				req.getSession().setAttribute("zc", zc);
			}else{
			/*没有错误，进行注册*/
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
